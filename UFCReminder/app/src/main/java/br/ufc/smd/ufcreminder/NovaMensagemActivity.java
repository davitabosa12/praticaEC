package br.ufc.smd.ufcreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class NovaMensagemActivity extends AppCompatActivity {

    EditText edtCorpo;
    Spinner spHorario, spLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_mensagem);
        edtCorpo = findViewById(R.id.edt_mensagem);
        spLocal = findViewById(R.id.sp_local);
        spHorario = findViewById(R.id.sp_horario);
    }


    public void onSalvar(View view){
        String mensagem = edtCorpo.getText().toString();
        Mensagem.Local local = Mensagem.Local.PICI;
        Mensagem.Horario horario = Mensagem.Horario.MANHA;
        int intLocal = spLocal.getSelectedItemPosition();
        int intHorario = spHorario.getSelectedItemPosition();
        if(intLocal == 0)
            local = Mensagem.Local.PICI;
        else if(intLocal == 1)
            local = Mensagem.Local.PORANGABUSSU;
        else if(intLocal == 2)
            local = Mensagem.Local.BENFICA;

        if(intHorario == 0)
            horario = Mensagem.Horario.MANHA;
        else if(intHorario == 1)
            horario = Mensagem.Horario.TARDE;
        else if(intHorario == 2)
            horario = Mensagem.Horario.NOITE;

        Mensagem m = new Mensagem(mensagem, local, horario);
        Intent intent = new Intent();
        intent.putExtra("mensagem", m);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onCancelar(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}
