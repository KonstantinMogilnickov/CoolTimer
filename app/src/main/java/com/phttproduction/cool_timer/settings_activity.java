package com.phttproduction.cool_timer;

import androidx.appcompat.app.AppCompatActivity;
import android.preference.PreferenceActivity;
import android.os.Bundle;

public class settings_activity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}