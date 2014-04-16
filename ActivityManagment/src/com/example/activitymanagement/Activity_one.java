package com.example.activitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Activity_one extends ActionBarActivity {

  EditText ad, soyad, yas, ileti;
  RadioButton erkek, kadin;
  Button gonder, temizle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_one);

    ad = (EditText) findViewById(R.id.adVeri);
    soyad = (EditText) findViewById(R.id.soyadVeri);
    yas = (EditText) findViewById(R.id.yasVeri);
    ileti = (EditText) findViewById(R.id.iletiVeri);

    erkek = (RadioButton) findViewById(R.id.cinsiyetErkek);
    kadin = (RadioButton) findViewById(R.id.cinsiyetKadin);

    gonder = (Button) findViewById(R.id.gonderDugme);
    temizle = (Button) findViewById(R.id.temizleDugme);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void gonder_tikla(View v) {
    try {
      Intent i = new Intent(getApplicationContext(), Activity_two.class);
      i.putExtra(Sabitler.AD, ad.getText().toString());
      i.putExtra(Sabitler.SOYAD, soyad.getText().toString());
      i.putExtra(Sabitler.YAS, Integer.parseInt(yas.getText().toString()));
      if (erkek.isChecked()) {
        i.putExtra(Sabitler.CINSIYET, getString(R.string.metin_erkek));
      } else if (kadin.isChecked()) {
        i.putExtra(Sabitler.CINSIYET, getString(R.string.metin_kadin));
      }
      i.putExtra(Sabitler.ILETI, ileti.getText().toString());
      startActivity(i);
    } catch (Exception e) {
      Toast.makeText(getApplicationContext(), getString(R.string.metin_hata),
          Toast.LENGTH_LONG).show();
    }
  }

  public void temizle_tikla(View v) {
    ad.setText("");
    soyad.setText("");
    yas.setText("");
    ileti.setText("");
    erkek.setChecked(true);
    kadin.setChecked(false);
  }

}
