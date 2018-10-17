/*
 * Copyright (c) 16 - 10 - 2018.
 * Email: jemmy.sapta14@gmail.com
 * Github: github.com/jemmycalak
 * Phone: 082269219485
 */

package com.calak.jemmy.technicaltestinveli.View.Menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.calak.jemmy.technicaltestinveli.Listener.OnMenu;
import com.calak.jemmy.technicaltestinveli.R;
import com.calak.jemmy.technicaltestinveli.View.Project.ProjectsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity implements OnMenu {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        InitUI();

    }

    private void InitUI() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        mainLayout();
        OnMenus(new ProjectsFragment());
    }

    private void mainLayout() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        ProjectsFragment projectsFragment = new ProjectsFragment();
        transaction.replace(R.id.mainFrame, projectsFragment, projectsFragment.getClass().getName());
        transaction.commit();
    }

    private void OnMenus(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrame, fragment, fragment.getClass().getName());
        if(fragment.getClass().getName() != new ProjectsFragment().getClass().getName()){
            transaction.addToBackStack(fragment.getClass().getName());
        }
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    public void OnMenu(Fragment fragment) {
        OnMenus(fragment);
    }
}
