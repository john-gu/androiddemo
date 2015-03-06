package com.tune.throneexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.throneexample.R;
import com.tune.alliances.TuneBanner;
import com.tune.alliances.TuneInterstitial;
import com.tune.mat.MobileAppTracker;

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
/*        MobileAppTracker.init(getApplicationContext(), getString(R.string.mat_advertiser_id), getString(R.string.mat_conversion_key));
        mat = MobileAppTracker.getInstance();
        
        // Collect Google Play Advertising ID
        new Thread(new Runnable() {
            @Override
            public void run() {
                // See sample code at http://developer.android.com/google/play-services/id.html
                try {
                    Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                    mat.setGoogleAdvertisingId(adInfo.getId(), adInfo.isLimitAdTrackingEnabled());
                } catch (Exception e) {
                    mat.setAndroidId(Secure.getString(getContentResolver(), Secure.ANDROID_ID));
                }
            }
        }).start();*/
        
        myView = (MyView) findViewById(R.id.my_view);
        myView.setBackgroundResource(R.drawable.sky);
        
        /*Button clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.clearCanvas();
                if (tuneInterstitial.hasLoaded()) {
                    tuneInterstitial.show();
                    tuneInterstitial.load();
                }
            }
        });*/
        
/*        tuneBanner = new TuneBanner(this);
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        mainLayout.addView(tuneBanner);
        tuneBanner.setPosition(TuneBannerPosition.BOTTOM_CENTER);
        tuneBanner.load();
*/        
/*        tuneInterstitial = new TuneInterstitial(this);
        tuneInterstitial.load();*/
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
//        mat.setReferralSources(this);
//        mat.measureSession();
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
