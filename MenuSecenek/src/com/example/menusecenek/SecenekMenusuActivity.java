package com.example.menusecenek;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecenekMenusuActivity extends Activity {
  ImageView goruntu;
  TextView yazi;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    goruntu = (ImageView) findViewById(R.id.imageView1);
    yazi = (TextView) findViewById(R.id.textView2);
    yazi.setText(R.string.bilgi); // kullanıcı bilgilendirme
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.secenekmenusu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Menudeki elemanlarin tiklanma durumu
    switch (item.getItemId()) {
    case R.id.secenek1:
      yazi.setText("");
      goruntu.setImageResource(R.drawable.unlu1a);
      Toast ileti = Toast.makeText(SecenekMenusuActivity.this, R.string.unlu1,
          Toast.LENGTH_LONG);
      ileti.setGravity(Gravity.CENTER, ileti.getXOffset() / 2,
          ileti.getYOffset() / 2);
      ileti.show();
      return true;

    case R.id.secenek2:
      yazi.setText(R.string.hakkinda1);
      return true;

    default:
      return super.onOptionsItemSelected(item);

    }
  }
}
