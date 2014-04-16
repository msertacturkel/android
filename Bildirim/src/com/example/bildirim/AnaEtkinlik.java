package com.example.bildirim;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

@SuppressLint("NewApi")
public class AnaEtkinlik extends ActionBarActivity {

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.ana);
  }
  
  public void eskiTikla(View v)
  {
    NotificationManager bildirimYoneticisi = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

  Notification bildirim = new Notification(R.drawable.ic_launcher, "Bildirim var!", System.currentTimeMillis());
    
    Intent detayEkraniIntent = new Intent(this, Detaylar.class);
    PendingIntent detayEkrani = PendingIntent.getActivity(this, 0, detayEkraniIntent, 0);

    bildirim.setLatestEventInfo(getApplicationContext(), "Başlık", "Detay", detayEkrani);
    
    bildirimYoneticisi.notify("bildirim_eski", 0, bildirim);
  }
  
  public void yeniTikla(View v)
  {
    NotificationManager bildirimYoneticisi = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    
    Notification bildirim = new Notification.Builder(getApplicationContext()).
                setTicker("Bildirim var!").
                setContentTitle("Başlık").
                setContentText("Detay").
                setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), Detaylar.class), 0)).
                setSmallIcon(R.drawable.ic_launcher).
                setAutoCancel(true).
                getNotification();
    
    bildirimYoneticisi.notify("bildirim_yeni", 0, bildirim);
  }
}
