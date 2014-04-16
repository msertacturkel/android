package com.androidce.sekmeogreniyorum;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class AnaEtkinlik extends TabActivity
{
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_etkinlik);
        
        TabHost sekmeAlani = getTabHost();
        TabHost.TabSpec sekme;
        Intent i;
        
        i = new Intent(this, SekmeBir.class);
        sekme = sekmeAlani.newTabSpec("sekme_bir_ad").setIndicator("sekme_bir_simge", getResources().getDrawable(R.drawable.simge1)).setContent(i);
        sekmeAlani.addTab(sekme);
        
        i = new Intent(this, SekmeIki.class);
        sekme = sekmeAlani.newTabSpec("sekme_iki_ad").setIndicator("sekme_iki_simge", getResources().getDrawable(R.drawable.simge2)).setContent(i);
        sekmeAlani.addTab(sekme);
        
        i = new Intent(this, SekmeUc.class);
        sekme = sekmeAlani.newTabSpec("sekme_uc_ad").setIndicator("sekme_uc_simge", getResources().getDrawable(R.drawable.simge3)).setContent(i);
        sekmeAlani.addTab(sekme);
        
        sekmeAlani.setCurrentTab(2);
    }
}