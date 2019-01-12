package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_library:
                    fragment = new LibraryFragment();
                    break;
                case R.id.navigation_diagram:
                    fragment = new DiagramFragment();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, fragment);
            transaction.commit();

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragment = new LibraryFragment();

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar =  findViewById(R.id.toolbar_library);
        setSupportActionBar(toolbar);

    }

    public void onAddLibraryBtn(View view){
        Intent intent = new Intent(MainActivity.this, ScoreEditActivity.class);
        intent.putExtra("edit_mode", 1);
        startActivity(intent);
    }

    public void onAddDiagramBtn(View view){
        Intent intent = new Intent(MainActivity.this, DiagramEditActivity.class);
        intent.putExtra("edit_mode", 1);
        startActivity(intent);
    }

}
