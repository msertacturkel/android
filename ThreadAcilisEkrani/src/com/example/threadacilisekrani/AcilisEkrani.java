package com.example.threadacilisekrani;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class AcilisEkrani extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.acilisekrani);
    Thread zamanlayici = new Thread() {
      @Override
      public void run() {
        try {
          sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          Intent etkinlik = new Intent(
              "com.example.threadacilisekrani.ETKINLIK");
          startActivity(etkinlik);
        }
      }
    };

    zamanlayici.start();
  }

}
