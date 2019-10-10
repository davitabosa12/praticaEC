package br.ufc.smd.ufcreminder;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

public class MensagemView extends ConstraintLayout {
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
        inflater.inflate(0, this);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
