package com.example.and_project.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.and_project.calendar.CalendarActivity;
import com.example.and_project.goals.GoalsActivity;
import com.example.and_project.login.LogInActivity;
import com.example.and_project.profile.ProfileActivity;
import com.example.and_project.R;
import com.example.and_project.stepCounter.StepCounterActivity;
import com.example.and_project.settings.SettingsActivity;
import com.example.and_project.stepCounter.StepsService;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FirebaseAuth.AuthStateListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        final ViewPager viewPager = findViewById(R.id.view_pager);

        MainFragmentsAdapter fragmentsAdapter = new MainFragmentsAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        viewPager.setAdapter(fragmentsAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
    {
        if(firebaseAuth.getCurrentUser() == null)
        {
            Intent intent = new Intent(getApplication(), LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                return true;
            case R.id.goals:
                Intent goalIntent = new Intent(MainActivity.this, GoalsActivity.class);
                startActivity(goalIntent);
                return true;
            case R.id.calendar:
                Intent calendarIntent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(calendarIntent);
                return true;
            case R.id.stepCounter:
                Intent stepCounterIntent = new Intent(MainActivity.this, StepCounterActivity.class);
                startActivity(stepCounterIntent);
                return true;
            case R.id.settings:
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.logOut:
                Intent logOutIntent = new Intent(MainActivity.this, LogInActivity.class);
                startActivityForResult(logOutIntent, 2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addMeal(View view)
    {
        Intent intent = new Intent(this, AddMealActivity.class);
        startActivity(intent);
    }
}
