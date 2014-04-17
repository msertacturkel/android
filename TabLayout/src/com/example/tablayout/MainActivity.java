package com.example.tablayout;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TabHost tabHost = getTabHost();
    TabSpec homespec = tabHost.newTabSpec("Anasayfa");
    homespec.setIndicator("Anasayfa",
        getResources().getDrawable(R.drawable.hometab));
    Intent videosIntent = new Intent(this, HomeActivity.class);
    homespec.setContent(videosIntent);
    TabSpec photospec = tabHost.newTabSpec("Fotoğraflar");
    photospec.setIndicator("Fotoğraflar",
        getResources().getDrawable(R.drawable.phototab));
    Intent photosIntent = new Intent(this, PhotoActivity.class);
    photospec.setContent(photosIntent);
    TabSpec songspec = tabHost.newTabSpec("Müzik");
    songspec.setIndicator("Müzik",
        getResources().getDrawable(R.drawable.musictab));
    Intent songsIntent = new Intent(this, MusicActivity.class);
    songspec.setContent(songsIntent);

    tabHost.addTab(homespec);
    tabHost.addTab(photospec);
    tabHost.addTab(songspec);
  }

}
