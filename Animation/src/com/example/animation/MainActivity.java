package com.example.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements AnimationListener {
  ImageView imageview;
  Button donbuyut;
  Animation Animasyonumuz;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    imageview = (ImageView) findViewById(R.id.imageView1);
    donbuyut = (Button) findViewById(R.id.btnStart);
    Animasyonumuz = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.anim);

    Animasyonumuz.setAnimationListener(this);
    donbuyut.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        imageview.startAnimation(Animasyonumuz);

      }
    });

  }

  @Override
  public void onAnimationEnd(Animation animation) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onAnimationRepeat(Animation animation) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onAnimationStart(Animation animation) {
    // TODO Auto-generated method stub

  }

}
