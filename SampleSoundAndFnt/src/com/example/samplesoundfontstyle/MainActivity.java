package com.example.samplesoundfontstyle;

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
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements OnClickListener {
  Button btn1, btn2;
  ImageView imgview;
  MediaPlayer c1, c2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    btn1 = (Button) findViewById(R.id.btn1);
    btn2 = (Button) findViewById(R.id.btn2);
    c1 = MediaPlayer.create(this, R.raw.dogrucevap);
    c2 = MediaPlayer.create(this, R.raw.yanliscevap);
    btn1.setOnClickListener(this);
    btn2.setOnClickListener(this);
    

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

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater
          .inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.btn1:
      c1.start();
      break;
    case R.id.btn2:
      c2.start();
      break;
    default:
      break;
    }
  }

}
