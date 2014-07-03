package com.xlagunas.mundial;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import models.Team;

/**
 * Created by Z77-DS3H on 03/07/2014.
 */
public class TeamBaseAdapter extends BaseAdapter {
    private List<Team> mTeams = null;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
