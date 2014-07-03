package com.xlagunas.mundial;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import models.Team;


public class LandingActivity extends Activity {
    private final String LOGTAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Button teamsButton = (Button) findViewById(R.id.teamsButton);
        teamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeamsFetch tf = new TeamsFetch();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), TestFragmentActivity.class);
                startActivityForResult(intent, 200);
            }
        });

        Button groupsButton = (Button) findViewById(R.id.groupsButton);
        groupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), GroupsActivity.class);
                startActivityForResult(intent, 200);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing, menu);
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

    private class TeamsFetch extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection mConnection = null;
            BufferedReader mReader = null;
            try {
                URL url = new URL(strings[0]);
                mConnection = (HttpURLConnection) url.openConnection();
                mConnection.connect();
                if (mConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                    mReader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
                    return readTextStream(mReader);
                }
                else {
                    Log.e(LOGTAG, "Bad Request" +mConnection.getResponseCode());
                    return null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e(LOGTAG, e.getCause().toString());
                return null;
            } catch (IOException e) {
                Log.e(LOGTAG, e.getCause().toString());
                return null;
            } finally {
                if (mConnection != null)
                    mConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }

        private String readTextStream(BufferedReader reader) throws IOException {
            StringBuilder sb = new StringBuilder();
            if (reader != null) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                Type type =  new TypeToken<ArrayList<Team>>() { }.getType();
                List<Team> teams = new Gson().fromJson(sb.toString(), type);
                return sb.toString();
            } else return null;
        }
    }
}
