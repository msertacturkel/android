package com.example.serviskullanimi;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button dugme = (Button) findViewById(R.id.dugmeServis);

  }

  private boolean servisCalisiyorMu() {
    ActivityManager servisYonetici = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    for (RunningServiceInfo servis : servisYonetici
        .getRunningServices(Integer.MAX_VALUE)) {
      if (getApplication().getPackageName().equals(
          servis.service.getPackageName())) {
        return true;
      }
    }
    return false;
  }

  public void dugmeServisTikla(View v) {
    Button dugme = (Button) v;

    if (servisCalisiyorMu()) {
      stopService(new Intent(this, Zamanlayici.class));
      dugme.setText(getString(R.string.baslat));
      // Durdur
    } else {
      startService(new Intent(this, Zamanlayici.class));
      dugme.setText(getString(R.string.durdur));
      // calistir
    }
  }

}
