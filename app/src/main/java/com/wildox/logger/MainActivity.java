package com.wildox.logger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.wildox.loghere.LogHere;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Setting the debug mode like this would avoid
         * printing the logs if it's in release mode
          */
        LogHere.setDebugMode(BuildConfig.DEBUG);
        LogHere.setPackageName(getPackageName());

        FloatingActionButton seeLogs = (FloatingActionButton) findViewById(R.id.see_logs);
        seeLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogHere.e("anything");
                thisIsMe();
            }
        });
    }

    void thisIsMe() {
        LogHere.e("zero parent", 0);

        LogHere.e("one parent", 1);
        thisIsNotMe();
    }

    void thisIsNotMe() {
        LogHere.e("two parent", 2);
    }
}
