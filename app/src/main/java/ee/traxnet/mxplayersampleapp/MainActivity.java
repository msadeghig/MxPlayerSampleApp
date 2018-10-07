package ee.traxnet.mxplayersampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public class MainActivity extends Activity {

    private final String TAG = "DFP_TEST";

    private Button loadBanner;
    private Button loadIntrestitialBanner;
    private PublisherAdView adView;
    private PublisherInterstitialAd mPublisherInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView = findViewById(R.id.publisherAdView);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i(TAG, "Ad Loaded");
                Toast.makeText(MainActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e(TAG, "Ad Failed to Load: errorCode " + errorCode);
                Toast.makeText(MainActivity.this, "Ad Load Failed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdOpened() {
                Log.i(TAG, "Ad Opened");
                Toast.makeText(MainActivity.this, "Ad Opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Log.i(TAG, "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                Log.i(TAG, "Ad Closed");
                Toast.makeText(MainActivity.this, "Ad Closed", Toast.LENGTH_SHORT).show();
            }
        });

        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId("/21747751703/mxTestInterstetialAdUnit");

        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i(TAG, "iAd Loaded");
                Toast.makeText(MainActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();

                mPublisherInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e(TAG, "iAd Failed to Load: errorCode " + errorCode);
                Toast.makeText(MainActivity.this, "Ad Load Failed", Toast.LENGTH_LONG).show();

                mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
            }

            @Override
            public void onAdOpened() {
                Log.i(TAG, "iAd Opened");
                Toast.makeText(MainActivity.this, "Ad Opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Log.i(TAG, "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                Log.i(TAG, "iAd Closed");
                Toast.makeText(MainActivity.this, "Ad Closed", Toast.LENGTH_SHORT).show();
            }
        });

        loadBanner = findViewById(R.id.load_banner);
        loadIntrestitialBanner = findViewById(R.id.load_interstetial_banner);

        loadBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAd(adView);
            }
        });

        loadIntrestitialBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
            }
        });


    }

    private void loadAd(PublisherAdView adView) {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}
