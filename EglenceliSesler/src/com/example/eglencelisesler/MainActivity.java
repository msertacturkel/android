package com.example.eglencelisesler;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener {
  ToggleButton togg1, togg2, togg3, togg4, togg5, togg6, togg7, togg8, togg9;
  MediaPlayer sincap, seytanikahkaha, bateri, kalpatisi, trololo, kedicanini,
      haha, alkis, dedeler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    togg1 = (ToggleButton) findViewById(R.id.togg1);
    togg2 = (ToggleButton) findViewById(R.id.togg2);
    togg3 = (ToggleButton) findViewById(R.id.togg3);
    togg4 = (ToggleButton) findViewById(R.id.togg4);
    togg5 = (ToggleButton) findViewById(R.id.togg5);
    togg6 = (ToggleButton) findViewById(R.id.togg6);
    togg7 = (ToggleButton) findViewById(R.id.togg7);
    togg8 = (ToggleButton) findViewById(R.id.togg8);
    togg9 = (ToggleButton) findViewById(R.id.togg9);

    sincap = MediaPlayer.create(this, R.raw.dramatiksincap);
    seytanikahkaha = MediaPlayer.create(this, R.raw.seytanikahkaha);
    bateri = MediaPlayer.create(this, R.raw.bateri);
    kalpatisi = MediaPlayer.create(this, R.raw.kalpatisi);
    trololo = MediaPlayer.create(this, R.raw.trololo);
    kedicanini = MediaPlayer.create(this, R.raw.kedicanini);
    haha = MediaPlayer.create(this, R.raw.haha);
    alkis = MediaPlayer.create(this, R.raw.alkis);
    dedeler = MediaPlayer.create(this, R.raw.dedeler);

    togg1.setOnClickListener(this);
    togg2.setOnClickListener(this);
    togg3.setOnClickListener(this);
    togg4.setOnClickListener(this);
    togg5.setOnClickListener(this);
    togg6.setOnClickListener(this);
    togg7.setOnClickListener(this);
    togg8.setOnClickListener(this);
    togg9.setOnClickListener(this);

  }


  @Override
  public void onClick(View v) {
    switch (v.getId()) {

    case R.id.togg1:
      if (togg1.isChecked()) {
        sincap.start();
        sincap.setLooping(true);
      } else
        sincap.pause();
      break;

    case R.id.togg2:
      if (togg2.isChecked()) {
        seytanikahkaha.start();
        seytanikahkaha.setLooping(true);
      } else
        seytanikahkaha.pause();
      break;
    case R.id.togg3:
      if (togg3.isChecked()) {
        bateri.start();
        bateri.setLooping(true);
      } else
        bateri.pause();
      break;

    case R.id.togg4:
      if (togg4.isChecked()) {
        kalpatisi.start();
        kalpatisi.setLooping(true);
      } else
        kalpatisi.pause();
      break;

    case R.id.togg5:
      if (togg5.isChecked()) {
        trololo.start();
        trololo.setLooping(true);
      } else
        trololo.pause();
      break;

    case R.id.togg6:
      if (togg6.isChecked()) {
        kedicanini.start();
        kedicanini.setLooping(true);
      } else
        kedicanini.pause();
      break;

    case R.id.togg7:
      if (togg7.isChecked()) {
        haha.start();
        haha.setLooping(true);
      } else
        haha.pause();
      break;

    case R.id.togg8:
      if (togg8.isChecked()) {
        alkis.start();
        alkis.setLooping(true);
      } else
        alkis.pause();
      break;

    case R.id.togg9:
      if (togg9.isChecked()) {
        dedeler.start();
        dedeler.setLooping(true);
      } else
        dedeler.pause();
      break;
    }

  }
}
