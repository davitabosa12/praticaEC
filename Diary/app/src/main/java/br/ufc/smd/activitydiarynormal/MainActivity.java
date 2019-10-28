package br.ufc.smd.activitydiarynormal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    


	void ativarFences() {
        // Ative as fences aqui
    }

    void desativarFences() {
        // Desative as fences aqui
    }
    
    /***************************** NÃO EDITAR O CÓDIGO A PARTIR DESTE PONTO *****************************/
    ActivityTimeView atvStill, atvMoving;
    public static final String STILL_FENCE_KEY = "stillStarting";
    public static final String MOVING_FENCE_KEY = "movingStarting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        atvStill = findViewById(R.id.tv_still);
        atvMoving = findViewById(R.id.tv_moving);
        atvStill.setTime(15684);

        atualizarDados();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_iniciar:
                iniciarMonitoramento();
                break;
            case R.id.action_reset:
                AppDb.getInstance(this).clear();
            case R.id.action_atualizar:
                atualizarDados();
                break;
            case R.id.action_desligar_fences:
                pararMonitoramento();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void pararMonitoramento() {
        desativarFences();
        new Stopwatch(this).stopMonitoring();
    }

    private void iniciarMonitoramento() {
        ativarFences();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        		// TODO: Permissao de ForegroundService para Android Pie

        	} else{
        		startForegroundService(new Intent(this, StopwatchService.class));	
        	}
            
        } else {
            startService(new Intent(this, StopwatchService.class));
        }
    }


    void atualizarDados() {
        AppDb db = AppDb.getInstance(this);
        //TODO: remove * 1000
        long timeMoving = db.getMovingTime() * 1000;
        long timeStill = db.getStillTime() * 1000;
        atvMoving.setTime(timeMoving);
        atvStill.setTime(timeStill);
        atvMoving.setActivity(ActivityTimeView.Activities.RUNNING);
        atvStill.setActivity(ActivityTimeView.Activities.STILL);


    }

    
}
