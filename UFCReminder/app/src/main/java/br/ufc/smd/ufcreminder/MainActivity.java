package br.ufc.smd.ufcreminder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.FenceClient;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.LocationFence;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MensagemView.OnDeletePressed {

    FloatingActionButton fabNovaMensagem;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabNovaMensagem = findViewById(R.id.fab_nova_msg);

        ll = findViewById(R.id.ll_scroll);
        atualizarTela();
        ativarFences();

    }

    private void ativarFences() {
        FenceClient client = Awareness.getFenceClient(this);
        LocaisUFC locaisUFC = new LocaisUFC();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
            }
            return;
        }
        AwarenessFence pici = LocationFence.entering(locaisUFC.PICI.getLatitude(), locaisUFC.PICI.getLongitude(), 500);
        AwarenessFence benfica = LocationFence.entering(locaisUFC.BENFICA.getLatitude(), locaisUFC.BENFICA.getLongitude(), 500);
        AwarenessFence porangabussu = LocationFence.entering(locaisUFC.PORANGABUSSU.getLatitude(), locaisUFC.PORANGABUSSU.getLongitude(), 500);
        
    }

    @Override
    public void onDeletePressed(Mensagem mensagem) {
        if(mensagem != null){
            AppDb.getInstance(this).mensagemDao().delete(mensagem);
            atualizarTela();

        }
        Toast.makeText(this, "DELETE PRESSED", Toast.LENGTH_SHORT).show();
    }

    public void fabClicked(View view){
        startActivityForResult(new Intent(this, NovaMensagemActivity.class), 777);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 777){
            if(resultCode == RESULT_OK){
                Mensagem m = (Mensagem) data.getSerializableExtra("mensagem");
                if(m != null)
                    AppDb.getInstance(this).mensagemDao().insert(m);
                    atualizarTela();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ativarFences();
    }

    private void atualizarTela() {
        ll.removeAllViews();
        List<Mensagem> cadastradas = AppDb.getInstance(this).mensagemDao().getAll();
        if(cadastradas.isEmpty()){
            TextView txv = new TextView(this);
            txv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            txv.setText("Nenhuma noticia cadastrada");
            ll.addView(txv);
        }
        for(Mensagem mensagem : cadastradas){
            MensagemView mensagemView = new MensagemView(this);
            mensagemView.setMensagem(mensagem);
            mensagemView.setDeletePressed(this);
            ll.addView(mensagemView);
        }
    }
}
