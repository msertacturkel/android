package com.example.browserviapicture;

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
import android.widget.ImageView;
import android.os.Build;

public class BrowseWebPage extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageView img = (ImageView) findViewById(R.id.tikla);

  }

  public void resmeTikla(View v) {
    Intent intent = new Intent(getApplicationContext(), InternetSitesi.class);
    startActivity(intent);

  }

}
