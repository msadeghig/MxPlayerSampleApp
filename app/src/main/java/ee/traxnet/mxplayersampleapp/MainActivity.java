package ee.traxnet.mxplayersampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public class MainActivity extends AppCompatActivity{

    private final String TAG = "DFP_TEST";

    private Button loadBanner;
    private PublisherAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadBanner = findViewById(R.id.load_banner);

        adView = findViewById(R.id.publisherAdView);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i(TAG, "Ad Loaded");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadAd();
                            }
                        });
                    }
                }).start();
                Toast.makeText(MainActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e(TAG, "Ad Failed to Load: errorCode " + errorCode);
                Toast.makeText(MainActivity.this, "Ad Load Failed", Toast.LENGTH_LONG).show();
                loadAd();
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

        loadAd();
    }


    private void loadAd(){
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}
