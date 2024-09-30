package vn.edu.usth.usthweather.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.usth.usthweather.R;
import vn.edu.usth.usthweather.adapter.HomeFragmentPagerAdapter;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = "Weather";
    public static final String NETWORK_RESPONSE_KEY = "NETWORK_RESPONSE_KEY";

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.i(TAG, "ON_CREATE");
        initViewPager();
        mMediaPlayer = MediaPlayer.create(this, R.raw.cardigan);
        mMediaPlayer.start();
        initToolBar();
        //requestNetwork();
        requestNetworkByAsyncTask();
    }

    private void requestNetworkByAsyncTask() {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {

            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                Toast.makeText(getApplicationContext(),
                        "Request Network....", Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }

    private void requestNetwork() {
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                String content = msg.getData().getString(NETWORK_RESPONSE_KEY);
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bundle mBundle = new Bundle();
                mBundle.putString(NETWORK_RESPONSE_KEY, "Request Network....");
                Message msg = new Message();
                msg.setData(mBundle);
                handler.sendMessage(msg);
            }
        });
        thread.start();
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.weather_toolbar);
        toolbar.inflateMenu(R.menu.weather_menu);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(item -> {
            int itemMenuId = item.getItemId();
            if (itemMenuId == R.id.ic_refresh) {
                Toast.makeText(this, "Refreshing process...", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemMenuId == R.id.ic_more) {
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;
            } else {
                Toast.makeText(this, "Not found menu item", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void initViewPager() {
        ViewPager2 pager = findViewById(R.id.pager2);
        //HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle());
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this);
        pager.setAdapter(adapter);
        TabLayout tablayout = findViewById(R.id.tab_layout);
        String[] titles = new String[]{"Hà Nội, Việt Nam", "Paris, Pháp", "Tokyo, Nhật Bản"};
        TabLayoutMediator layoutMediator = new TabLayoutMediator(tablayout, pager,
                ((tab, position) -> {
                    tab.setText(titles[position]);
                }));
        layoutMediator.attach();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "ON_START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "ON_RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "ON_PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "ON_STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ON_DESTROY");
    }
}