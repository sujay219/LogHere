package com.wildox.logger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wildox.loghere.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting the debug mode like this would avoid
        // printing the logs if it's in release mode
        // Choose one of these ..
        Log.initialize(BuildConfig.DEBUG, "wildox");
        Log.e("haha", "this");

        FloatingActionButton seeLogs = (FloatingActionButton) findViewById(R.id.see_logs);
        seeLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisIsMe();
            }
        });
    }

    void thisIsMe() {
        Log.e("mesg");
        thisIsNotMe();
    }

    void thisIsNotMe() {

        Log.e("meg", 1);
    }
}
