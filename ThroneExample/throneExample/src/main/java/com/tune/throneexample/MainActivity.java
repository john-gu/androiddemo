package com.tune.throneexample;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.throneexample.R;
import com.tune.alliances.TuneAdOrientation;
import com.tune.alliances.TuneBanner;
import com.tune.alliances.TuneBannerPosition;
import com.tune.alliances.TuneInterstitial;
import com.tune.mat.MobileAppTracker;

import static com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import static com.google.android.gms.ads.identifier.AdvertisingIdClient.getAdvertisingIdInfo;

public class MainActivity
    extends ActionBarActivity {
    
    private MyView myView;
    
    private MobileAppTracker mat;
    private TuneBanner tuneBanner;
    private TuneInterstitial tuneInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Init MAT
        MobileAppTracker.init(getApplicationContext(), getString(R.string.mat_advertiser_id), getString(R.string.mat_conversion_key));
        mat = MobileAppTracker.getInstance();
        mat.setPackageName("com.test");

        // Collect Google Play Advertising ID
        new Thread(new Runnable() {
            @Override
            public void run() {
                // See sample code at http://developer.android.com/google/play-services/id.html
                try {
                    Info adInfo = getAdvertisingIdInfo(getApplicationContext());
                    mat.setGoogleAdvertisingId(adInfo.getId(), adInfo.isLimitAdTrackingEnabled());
                } catch (Exception e) {
                    mat.setAndroidId(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
                }
            }
        }).start();
        
        myView = (MyView) findViewById(R.id.my_view);
        myView.setBackgroundResource(R.drawable.sky);
        
        Button clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //myView.clearCanvas();
                if (tuneInterstitial.hasLoaded()) {
                    tuneInterstitial.show();
                    tuneInterstitial.load();
                }
            }
        });
        
        tuneBanner = new TuneBanner(this);
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        mainLayout.addView(tuneBanner);
        tuneBanner.setPosition(TuneBannerPosition.BOTTOM_CENTER);
        tuneBanner.load();

        tuneInterstitial = new TuneInterstitial(this, TuneAdOrientation.PORTRAIT_ONLY);
        tuneInterstitial.load();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
//        mat.setReferralSources(this);
//        mat.measureSession();

        tuneBanner.resume();
    }

    @Override
    protected void onPause() {
        tuneBanner.pause();

        super.onPause();
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
        if ( id == R.id.action_settings ) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
