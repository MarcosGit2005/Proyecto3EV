package com.example.proyecto3ev_cliente;


import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Arrays;
import java.util.List;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.preferencias, rootKey);

        // Modificacion de la vista de preferencias por codigo

        // EditTextPreference
        final EditTextPreference editTextEndPoint = findPreference("endPoint");
        editTextEndPoint.setSummary("Actualmente: " + GestionPreferencias.getInstance().getEndPoint(getContext()));

        editTextEndPoint.setOnPreferenceChangeListener((preference, newValue) -> {
            editTextEndPoint.setSummary("Actualmente: " + newValue);
            return true;
        });

        final EditTextPreference editTextRutaServer = findPreference("rutaServer");
        editTextRutaServer.setSummary("Actualmente: " + GestionPreferencias.getInstance().getRutaServer(getContext()));

        editTextRutaServer.setOnPreferenceChangeListener((preference, newValue) -> {
            editTextRutaServer.setSummary("Actualmente: " + newValue);
            return true;
        });

        // Theme preferences with ListPreference
        ListPreference themePreference = getPreferenceManager().findPreference(getString(R.string.settings_theme_key));
        if (themePreference.getValue() == null) {
            themePreference.setValue(ThemeSetup.Mode.DEFAULT.name());
        }

        themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            ThemeSetup.applyTheme(ThemeSetup.Mode.valueOf((String) newValue));
            return true;
        });

        //Laguage preference
        final ListPreference language = findPreference("idioma");
        final List<String> language_entries = Arrays.asList(getResources().getStringArray(R.array.settings_language_entries));
        final List<String> language_values = Arrays.asList(getResources().getStringArray(R.array.settings_language_values));
        int position  = language_values.indexOf(GestionPreferencias.getInstance().getLanguage(getContext()));

        language.setSummary("Idioma: " + language_entries.get(position));

        language.setOnPreferenceChangeListener((preference, newValue) -> {

            int position1 = language_values.indexOf(newValue);
            language.setSummary("Idioma: " + language_entries.get(position1));

            return true;
        });
    }
}
