package com.example.testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestLayout testLayout = ((TestLayout) findViewById(R.id.mainTest));
        testLayout.setTest(new Test());
        testLayout.start();
    }
}
