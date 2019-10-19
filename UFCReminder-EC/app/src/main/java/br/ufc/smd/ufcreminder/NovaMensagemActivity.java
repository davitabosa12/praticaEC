package br.ufc.smd.ufcreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class NovaMensagemActivity extends AppCompatActivity {

    EditText edtCorpo;
    Spinner spHorario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_mensagem);
        edtCorpo = findViewById(R.id.edt_mensagem);
        spHorario = findViewById(R.id.sp_horario);
    }


    public void onSalvar(View view){
        String mensagem = edtCorpo.getText().toString();
        Mensagem.Horario horario = Mensagem.Horario.MANHA;
        int intHorario = spHorario.getSelectedItemPosition();
        if(intHorario == 0)
            horario = Mensagem.Horario.MANHA;
        else if(intHorario == 1)
            horario = Mensagem.Horario.TARDE;
        else if(intHorario == 2)
            horario = Mensagem.Horario.NOITE;

        Mensagem m = new Mensagem(mensagem, horario);
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
