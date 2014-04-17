package com.example.myflash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
 Camera camera;   // Kamera nesnesi oluşturuyoruz.
 Parameters parameters;  // kamera parametreleri için gerekli olan parametre değişkenini ve nesnesini bildiriyoruz. (declare etmek)
 @SuppressWarnings("deprecation")
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);   // uygulamamızın arayüzü dosyasını layout klasörü altındaki activity_main dosyası olacak şekilde ayarlıyoruz.

 Button opbuton = (Button) findViewById(R.id.opbutton);  // kullandığımız üç butona java dosyasından buton nesneleri yaratıp ulaşıyoruz.
 Button clbutton = (Button) findViewById(R.id.clbutton);
 Button btnexit = (Button) findViewById(R.id.btnexit);

 camera = Camera.open();  // burada kameramızı açıyoruz yani kameraya bağlanıyoruz. 
 parameters = camera.getParameters(); // kamera parametrelerini alıyoruz.
 camera.startPreview(); // kamera nın parametrelere göre iş yapmasını burada sağlıyoruz.
 Boolean VarFlash;   //  VarFlash değişkenimizi bildiriyoruz.

 /*
  * First check if device is supporting flashlight or not
  */
 VarFlash = getApplicationContext().getPackageManager()    
         .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);  // Kameramız flash a sahip mi? Onun bilgisini VarFlash değişkenine atıyoruz.

 if (!VarFlash) {
     // cihazın flashı yok!
     // bir alarm mesajı gösterip uygulamayı kapatıyoruz.
     AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
             .create();
     alert.setTitle("Hata");
     alert.setMessage("Cihazınız Flashı desteklemiyor!");
     alert.setButton("Tamam", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
             // uygulamayı kapatıyoruz
             finish();
         }
     });
     alert.show();   // alarm mesajını gösteriyoruz
     return;
 }

 opbuton.setOnClickListener(new OnClickListener() {

  public void onClick(View arg0) {
   // opbuton a dokunulduğunda kameranın parametrelerini flashı açacak şekilde ayarlayıp kameraya yolluyoruz.

   parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
       camera.setParameters(parameters);
       }  
 });

 clbutton.setOnClickListener(new OnClickListener() {

  public void onClick(View arg0) {

   // opbuton a dokunulduğunda kameranın parametrelerini flashı kapatacak şekilde ayarlayıp kameraya yolluyoruz.

   parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
       camera.setParameters(parameters);
       }

 });

 btnexit.setOnClickListener(new OnClickListener() {

  @Override
  public void onClick(View v) {

  // Çıkış butonuna dokunulduğunda uygulamayı kapatıyoruz.

   System.exit(0);

  }
 });

 }

 @Override
 protected void onDestroy() {
  super.onDestroy();
 }

 @Override
 protected void onRestart() {
  super.onRestart();
 }

 @Override
 protected void onStop() {
  super.onStop();

  // durma durumunda(mesela kullanıcı telefonunda geri butonuna basarsa) kamerayı serbest bırakıyoruz. Başka bir uygulama kullanmak isterse problem çıkmaması için.
  if (camera != null) {
   camera.release();
   camera = null;
  }
 }
}