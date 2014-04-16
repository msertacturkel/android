package com.example.arama;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

  }

  public void telefonAra(View v) {
    EditText numara=(EditText) findViewById(R.id.numara);
    String num;
    num=numara.getText().toString();
    
    Intent i = new Intent(Intent.ACTION_CALL);
    i.setData(Uri.parse("tel:03222391213"));
    startActivity(i);

  }

}
