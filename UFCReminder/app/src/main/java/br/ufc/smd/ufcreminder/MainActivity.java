package br.ufc.smd.ufcreminder;

import android.Manifest;
import android.app.PendingIntent;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.FenceClient;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.awareness.fence.LocationFence;
import com.google.android.gms.awareness.fence.TimeFence;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements MensagemView.OnDeletePressed {

    FloatingActionButton fabNovaMensagem;
    LinearLayout ll;


    private void ativarFences() {
        // crie e registre as fences aqui
        
    }
    public void desativarFences(){
        // desative as fences aqui
    }

    /***** Não editar abaixo **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabNovaMensagem = findViewById(R.id.fab_nova_msg);

        ll = findViewById(R.id.ll_scroll);
        atualizarTela();
        ativarFences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_ativa_fences:
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
                }
                break;
                }
                ativarFences();
                break;
            case R.id.action_desliga_fences:
                desativarFences();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    

    

    @Override
    public void onDeletePressed(Mensagem mensagem) {
        if(mensagem != null){
            AppDb.getInstance(this).mensagemDao().delete(mensagem);
            atualizarTela();

        }
        //Toast.makeText(this, "DELETE PRESSED", Toast.LENGTH_SHORT).show();
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
        if(grantResults[0] == PERMISSION_GRANTED)
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
