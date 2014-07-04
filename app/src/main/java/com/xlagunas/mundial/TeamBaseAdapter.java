package com.xlagunas.mundial;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import models.Team;

/**
 * Created by Z77-DS3H on 03/07/2014.
 */
public class TeamBaseAdapter extends BaseAdapter {
    private List<Team> mTeams = new ArrayList<Team>();
    private LayoutInflater mInflater = null;
    private Context mContext = null;
    private final String LOGTAG = this.getClass().getName();

    public TeamBaseAdapter(Context context) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        TeamListTask task = new TeamListTask();
        task.execute("http://worldcup.sfg.io/teams/");
    }

    @Override
    public int getCount() {
        return mTeams.size();
    }

    @Override
    public Team getItem(int i) {
        return mTeams.get(i);
    }

    @Override
    public long getItemId(int i) {
        //No recordo ben bé per a que servia...
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolderItem holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_team, viewGroup, false);
            holder = new ViewHolderItem();
            holder.flag = (ImageView) convertView.findViewById(R.id.contry_flag);
            holder.countryName = (TextView) convertView.findViewById(R.id.country_name);
            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolderItem) convertView.getTag();
        }

        Team team = getItem(i);
        holder.countryName.setText(String.format("%s - %s", team.getCountry(), team.getFifa_code()));
        int idResource = mContext.getResources()
                .getIdentifier(team.getFifa_code().toLowerCase(), "drawable", mContext.getPackageName());
            holder.flag.setImageResource(idResource);


        return convertView;
    }

    static class ViewHolderItem{
        ImageView flag;
        TextView countryName;
    }

    private class TeamListTask extends AsyncTask<String, Integer, List<Team>> {

        @Override
        protected List<Team> doInBackground(String... url) {
            HttpURLConnection mConnection = null;
            BufferedReader mReader = null;
            try {
                URL teamsUrl = new URL(url[0]);
                mConnection = (HttpURLConnection) teamsUrl.openConnection();
                mConnection.connect();
                if (mConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                    mReader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
                    return parseTeamsList(mReader);
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
        protected void onPostExecute(List<Team> teams) {
//            super.onPostExecute(teams);
            if (teams != null){
                mTeams = teams;
                notifyDataSetChanged();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(LOGTAG, "Progress: "+values);
        }

        private List<Team> parseTeamsList(BufferedReader reader) throws IOException{
            StringBuilder sb = new StringBuilder();
            if (reader != null) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                Type type =  new TypeToken<ArrayList<Team>>() { }.getType();
                List<Team> teams = new Gson().fromJson(sb.toString(), type);
                return teams;
            } else return null;
        }
    }
}


