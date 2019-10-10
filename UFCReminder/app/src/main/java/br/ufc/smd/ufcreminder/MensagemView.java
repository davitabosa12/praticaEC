package br.ufc.smd.ufcreminder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MensagemView extends ConstraintLayout {
    TextView txvMensagem, txvLocal, txvHorario;
    ImageButton btnDelete;
    Mensagem mensagem;
    OnDeletePressed deletePressed;

    public void setDeletePressed(OnDeletePressed deletePressed) {
        this.deletePressed = deletePressed;
    }

    public MensagemView(Context context) {
        super(context);
        init(context);
    }

    public MensagemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MensagemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mensagem_view, this);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {

        txvHorario = findViewById(R.id.txv_horario);
        txvLocal = findViewById(R.id.txv_campus);
        txvMensagem = findViewById(R.id.txv_mensagem_resumo);
        btnDelete = findViewById(R.id.ib_delete);

        btnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deletePressed != null){
                    deletePressed.onDeletePressed(mensagem);
                }
            }
        });

        super.onFinishInflate();
    }

    public void setMensagem(@NonNull Mensagem m){
        this.mensagem = m;
        txvHorario.setText(horarioString(m.getHorario()));
        txvLocal.setText(localString(m.getLocal()));
        txvMensagem.setText(m.getConteudo());
    }

    private String horarioString(Mensagem.Horario horario){
        switch (horario){
            case MANHA:
                return "Manhã";
            case TARDE:
                return "Tarde";
            case NOITE:
                return "Noite";
            default:
                return "";
        }
    }

    private String localString(Mensagem.Local local){
        switch (local){
            case PICI:
                return "Campus do Pici";
            case BENFICA:
                return "Campus Benfica";
            case PORANGABUSSU:
                return "Campus Porangabussu";
            default:
                return "";
        }
    }

    public interface OnDeletePressed{
        void onDeletePressed(Mensagem mensagem);
    }
}
