package com.example.cardgame.cardgame.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.ui.adapter.StatePagerAdapter;
import com.parse.ParseUser;

/**
 * Created by chenshiyu on 10/21/15.
 */
public class UserpageActivity extends AppCompatActivity {

    //Main user page

    private ImageView logout;
    private ParseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar_container);

        setupUi();
    }


    private void setupUi() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new StatePagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.view_pager_tabs);
        tabLayout.setupWithViewPager(viewPager);

        currentUser = ParseUser.getCurrentUser();

        logout = (ImageView) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UserpageActivity.this)
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Log out", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (currentUser != null) {
                                    ParseUser.logOut();
                                    if (ParseUser.getCurrentUser() != null) {
                                        showToast("log out failed");
                                    } else {
                                        showToast("log out success!!");
                                        navigateToMainPage();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });

        findViewById(R.id.create_appt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserpageActivity.this, createApptActivity.class);
                startActivity(intent);
            }
        });
    }

    private void navigateToMainPage() {
        Intent intent = new Intent(UserpageActivity.this, OnBoardingActivity.class);
        finish();
        startActivity(intent);
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

}
