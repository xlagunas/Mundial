package com.xlagunas.mundial;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import fragments.TeamsFragment;

import static com.xlagunas.mundial.R.layout.activity_test_fragment;

public class TestFragmentActivity extends Activity implements TeamsFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_test_fragment);
        FragmentManager fm = getFragmentManager();

        if (fm.findFragmentById(R.id.static_fragment) == null) {
            TeamsFragment list = TeamsFragment.newInstance("", "");
            fm.beginTransaction().add(android.R.id.content, list).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
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

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(getApplicationContext(), "Click!" +id,Toast.LENGTH_LONG).show();
    }
}
