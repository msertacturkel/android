package com.example.activitymanagement;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class Activity_two extends ActionBarActivity {

  TextView bilgi;
  Button geri;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two);
    bilgi = (TextView) findViewById(R.id.bilgiMetni);
    geri = (Button) findViewById(R.id.geriDugme);
    String bilgiMetni = "";

    if (getIntent().getExtras().getString(Sabitler.CINSIYET)
        .equals(getString(R.string.metin_erkek))) {
      bilgiMetni = getString(R.string.metin_bay);
    } else if (getIntent().getExtras().getString(Sabitler.CINSIYET)
        .equals(getString(R.string.metin_kadin))) {
      bilgiMetni = getString(R.string.metin_bayan);
    }

    bilgiMetni += " " + getIntent().getExtras().getString(Sabitler.AD) + " "
        + getIntent().getExtras().getString(Sabitler.SOYAD) + " ("
        + getIntent().getExtras().getInt(Sabitler.YAS) + ") "
        + getString(R.string.metin_diyor)
        + getIntent().getExtras().getString(Sabitler.ILETI);

    bilgi.setText(bilgiMetni);
  }

  public void geri_tikla(View v) {
    finish();
  }
}
