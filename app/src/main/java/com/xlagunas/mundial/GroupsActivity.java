package com.xlagunas.mundial;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.xlagunas.mundial.R;

public class GroupsActivity extends Activity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        setupTabs();
    }

    private void setupTabs() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ActionBar.Tab group1 = actionBar
                .newTab()
                .setText("Grup 1")
                .setTabListener(this);
        actionBar.addTab(group1);

        ActionBar.Tab group2 = actionBar
                .newTab()
                .setText("Grup 2")
                .setTabListener(this);
        actionBar.addTab(group2);

        ActionBar.Tab group3 = actionBar
                .newTab()
                .setText("Grup 3")
                .setTabListener(this);
        actionBar.addTab(group3);

        ActionBar.Tab group4 = actionBar
                .newTab()
                .setText("Grup 4")
                .setTabListener(this);
        actionBar.addTab(group4);

        actionBar.selectTab(group1);


    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }


}
