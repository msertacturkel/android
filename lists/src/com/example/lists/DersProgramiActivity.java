package com.example.lists;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class DersProgramiActivity extends ActionBarActivity {
  private ListView list;
  private String[] gunler = { "Pazartesi", "Sali", "Carsamba", "Persembe",
      "Cuma", "Cumartesi", "Pazar" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    list = (ListView) findViewById(R.id.dersliste);
    list.setAdapter(new ArrayAdapter<String>(this,
        android.R.layout.simple_expandable_list_item_1, gunler));
    list.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(DersProgramiActivity.this);
        switch(position){
        case 0:
        {
          adb.setMessage(getString(R.string.pazartesi));
          adb.setPositiveButton("Peki", null);
          adb.show();
          break;
        }
        case 1:
        {
          adb.setMessage(getString(R.string.pazartesi));
          adb.setPositiveButton("Peki", null);
          adb.show();
          break;
        }
        case 2:
        {
          adb.setMessage(getString(R.string.pazartesi));
          adb.setPositiveButton("Peki", null);
          adb.show();
          break;
        }
        case 3:
        {
          adb.setMessage(getString(R.string.pazartesi));
          adb.setPositiveButton("Peki", null);
          adb.show();
          break;
        }
        case 4:
        {
          adb.setMessage(getString(R.string.pazartesi));
          adb.setPositiveButton("Peki", null);
          adb.show();
          break;
        }
        case 5:
        {
          adb.setMessage(getString(R.string.pazartesi));
          adb.setPositiveButton("Peki", null);
          adb.show();
          break;
        }
        }
      }
    });

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

}
