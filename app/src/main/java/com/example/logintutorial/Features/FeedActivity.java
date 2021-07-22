package com.example.logintutorial.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.logintutorial.R;

public class FeedActivity extends AppCompatActivity {

    private String _token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        //TODO: Get the token from shared storage or content provider
        //e.g. _token = ContentProvider.get("Token");
        //TODO: Save it to a local variable

        //TODO: Call the /feed endpoint to retrieve Posts
    }
}