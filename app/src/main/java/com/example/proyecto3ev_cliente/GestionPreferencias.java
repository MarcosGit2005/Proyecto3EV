package com.example.proyecto3ev_cliente;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class GestionPreferencias {

    private SharedPreferences pref;
    private static GestionPreferencias gestionPreferencias;

    private GestionPreferencias(){

    }

    public static GestionPreferencias getInstance(){
        if(gestionPreferencias==null)
            gestionPreferencias = new GestionPreferencias();
        return gestionPreferencias;
    }

    private void inicializa(Context context) {
        if (pref == null)
            pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUnidades(Context context){
        inicializa(context);
        return pref.getString("unidades","standard");
    }

    public String getEndPoint(Context context){
        inicializa(context);
        return pref.getString("endPoint","/api");
    }

    public String getRutaServer(Context context){
        inicializa(context);
        return pref.getString("rutaServer","poner ruta cuando la tengamos");
    }

    public String getTheme(Context context){
        inicializa(context);
        return pref.getString(context.getString(R.string.settings_theme_key),ThemeSetup.Mode.DEFAULT.name());
    }

    public String getLanguage(Context context){
        inicializa(context);
        return pref.getString("idioma", "spanish") ;
    }
}