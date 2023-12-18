package com.phttproduction.cool_timer;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Загрузка настроек из XML-ресурса
        addPreferencesFromResource(R.xml.preference_screen);
    }
}
