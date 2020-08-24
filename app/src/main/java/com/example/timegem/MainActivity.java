package com.example.timegem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
 DrawerLayout mDrawerLayout;
 NavigationView mNavigationView;
 ActionBarDrawerToggle mToggleButton;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggleButton.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer);
        mNavigationView=findViewById(R.id.nav);

        mToggleButton=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mToggleButton.syncState();
        mNavigationView.setCheckedItem(R.id.StopWatch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new StopWatch()).commit();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                 switch (item.getItemId())
                 {
                     case R.id.StopWatch:fragment=new StopWatch();
                         break;

                     case R.id.Alarm:fragment=new AlarmFragment();
                         break;

                     case R.id.Timer:fragment=new TimerFragment();
                         break;
                 }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commit();
                return true;
            }
        });

    }
}
