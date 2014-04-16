package com.example.galery;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryActivity extends Activity {
  private ImageView foto;
  private Gallery gallery;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    gallery = (Gallery) findViewById(R.id.gallery);
    foto = (ImageView) findViewById(R.id.image1);

    gallery.setAdapter(new ImageAdapter(this));
    gallery.setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView parent, View v, int position, long id) {

        switch (position) {

        case 0:
          foto.setImageResource(R.drawable.ornek1);
          break;

        case 1:
          foto.setImageResource(R.drawable.ornek2);
          break;

        case 2:
          foto.setImageResource(R.drawable.ornek3);
          break;

        case 3:
          foto.setImageResource(R.drawable.ornek4);
          break;
        }
      }
    });

  }

  public class ImageAdapter extends BaseAdapter {
    int mGalleryItemBackground;
    private Context mContext;
    private Integer[] mImageIds = { R.drawable.ornek1, R.drawable.ornek2,
        R.drawable.ornek3, R.drawable.ornek4, };

    public ImageAdapter(Context c) {
      mContext = c;
      TypedArray attr = mContext
          .obtainStyledAttributes(R.styleable.GaleriOlusturma);
      mGalleryItemBackground = attr.getResourceId(
          R.styleable.GaleriOlusturma_android_galleryItemBackground, 0);
      attr.recycle();
    }

    public int getCount() {
      return mImageIds.length;
    }

    public Object getItem(int position) {
      return position;
    }

    public long getItemId(int position) {
      return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
      ImageView imageView = new ImageView(mContext);
      imageView.setImageResource(mImageIds[position]);
      imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      imageView.setBackgroundResource(mGalleryItemBackground);

      return imageView;
    }
  }

}
