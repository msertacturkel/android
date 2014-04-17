package com.example.serviskullanimi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class Zamanlayici extends Service {
  Timer zamanlayici;
  Handler yardimci;
  public static long ZAMAN = 1000;

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    zamanlayici = new Timer();
    yardimci = new Handler(Looper.getMainLooper());
    zamanlayici.scheduleAtFixedRate(new TimerTask() {

      @Override
      public void run() {
        bilgiVer();
      }
    }, 0, ZAMAN);

  }

  private void bilgiVer() {
    long zaman = System.currentTimeMillis();
    SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy,EEEE /HH:mm");
    final String sonuc = format.format(new Date(zaman));
    yardimci.post(new Runnable() {

      @Override
      public void run() {
        Toast.makeText(Zamanlayici.this, sonuc, Toast.LENGTH_LONG).show();
      }
    });
  }

  @Override
  public void onDestroy() {
    zamanlayici.cancel();
    super.onDestroy();
  }
}
