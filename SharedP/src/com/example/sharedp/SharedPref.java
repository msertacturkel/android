package com.example.sharedp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.preference.PreferenceManager;

import com.example.sabitler.Sabitler;

public class SharedPref extends Activity {
  CheckBox secimKutusu;
  SeekBar kaydirmaCubugu;
  EditText metinKutusu;
  Button dugmeKaydet, dugmeSifirla;
  SharedPreferences ayarlar;
  
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        secimKutusu = (CheckBox) findViewById(R.id.cb);
        kaydirmaCubugu = (SeekBar) findViewById(R.id.sb);
        metinKutusu = (EditText) findViewById(R.id.tb);
        dugmeKaydet = (Button) findViewById(R.id.kaydet);
        dugmeSifirla = (Button) findViewById(R.id.sifirla);
        
        ayarlar = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        
        ayarOku();
    }
    
    public void ayarla(boolean secim, int kaydirma, String metin)
    {
      SharedPreferences.Editor e = ayarlar.edit();
    
      e.putBoolean(Sabitler.SECIMKUTUSU, secim);
      e.putInt(Sabitler.KAYDIRMACUBUGU, kaydirma);
      e.putString(Sabitler.METINKUTUSU, metin);
    
      e.commit();
    }
    
    public void ayarOku()
    {
      secimKutusu.setChecked(ayarlar.getBoolean(Sabitler.SECIMKUTUSU, true));
      kaydirmaCubugu.setProgress(ayarlar.getInt(Sabitler.KAYDIRMACUBUGU, 0));
      metinKutusu.setText(ayarlar.getString(Sabitler.METINKUTUSU, ""));
    }
    
    public void kaydet(View v)
    {
      ayarla(secimKutusu.isChecked(), kaydirmaCubugu.getProgress(), metinKutusu.getText().toString());
      Toast.makeText(getApplicationContext(), getString(R.string.text_kaydedildi), Toast.LENGTH_LONG).show();
    }
    
    public void sifirla(View v)
    {
      secimKutusu.setChecked(true);
      kaydirmaCubugu.setProgress(0);
      metinKutusu.setText("");
      
      ayarla(true, 0, "");
      Toast.makeText(getApplicationContext(), getString(R.string.text_sifirlandi), Toast.LENGTH_LONG).show();
    }
}
