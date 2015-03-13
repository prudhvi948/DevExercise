package com.devexercise.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.devexercise.app.services.AccountsOperation;
import com.devexercise.app.services.PieChartOperation;


public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 5000;
    private static final String URL1 = "https://appem.totango.com/api/v1/search/accounts/health_dist";
    private static final String URL2 = "https://appem.totango.com/api/v1/search/accounts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.splash_screen);
        imageView.setImageResource(R.drawable.totango_logo);

        new PieChartOperation(this).execute(URL1); //Gets JSON data for PieChart
        new AccountsOperation(this).execute(URL2); //Gets JSON data for ListView


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
