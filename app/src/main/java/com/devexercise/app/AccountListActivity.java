package com.devexercise.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.devexercise.app.adapters.AccountsArrayAdapter;
import com.devexercise.app.models.AccountsData;

import java.util.List;


public class AccountListActivity extends Activity {

    public static List<AccountsData> accountsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        //Creating ListView and setting the ArrayAdapter to it
        ListView listView = (ListView) findViewById(R.id.accounts);
        AccountsArrayAdapter accountsArrayAdapter = new AccountsArrayAdapter(this, accountsArrayList);
        listView.setAdapter(accountsArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_list, menu);
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
