package br.ufc.smd.activitydiarynormal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import static br.ufc.smd.activitydiarynormal.ActivityLog.RUNNING;
import static br.ufc.smd.activitydiarynormal.ActivityLog.STARTING;
import static br.ufc.smd.activitydiarynormal.ActivityLog.STILL;
import static br.ufc.smd.activitydiarynormal.ActivityLog.STOPPING;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppDatabase database = AppDatabase.getInstance(this);
        ActivityLog log1, log2, log3;
        log1 = new ActivityLog(STILL, System.currentTimeMillis(), STARTING);
        log2 = new ActivityLog(STILL, System.currentTimeMillis(), STOPPING);
        log3 = new ActivityLog(RUNNING, System.currentTimeMillis(), STARTING);
        database.activityLogDAO().insertAll(log1, log2, log3);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (id){
            case R.id.action_read:
                List<ActivityLog> logs = AppDatabase.getInstance(this).activityLogDAO().getAll();

                for(ActivityLog log : logs){
                    Log.d("Logs", "onOptionsItemSelected: " + log.toString());
                }
        }

        return super.onOptionsItemSelected(item);
    }
}
