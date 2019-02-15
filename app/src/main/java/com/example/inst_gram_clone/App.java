package com.example.inst_gram_clone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("TDZ4pMCo4KHfejGcd1O34JSQxvDuBRxHe2HFqDZZ")
                // if defined
                .clientKey("6SytVCsJQdnPghTVdelpG0lJxkV36DMdxbxD2yCN")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
