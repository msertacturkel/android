package com.example.browserviapicture;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.os.Build;

public class InternetSitesi extends ActionBarActivity {
  WebView wv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.internet);
    wv = (WebView) findViewById(R.id.webView1);
    wv.loadUrl("http://www.google.com");

  }

  public void resmeTikla(View v) {

  }

}
