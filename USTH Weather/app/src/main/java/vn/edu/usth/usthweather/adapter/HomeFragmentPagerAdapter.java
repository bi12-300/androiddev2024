package vn.edu.usth.usthweather.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.usthweather.fragment.weather_forcast_fragment.WeatherAndForecastFragmentHaNoi;
import vn.edu.usth.usthweather.fragment.weather_forcast_fragment.WeatherAndForecastFragmentParis;
import vn.edu.usth.usthweather.fragment.weather_forcast_fragment.WeatherAndForecastFragmentTokyo;

public class HomeFragmentPagerAdapter extends FragmentStateAdapter {

    public HomeFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public HomeFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new WeatherAndForecastFragmentHaNoi();
            case 1 : return new WeatherAndForecastFragmentParis();
            case 2 : return new WeatherAndForecastFragmentTokyo();
        }
        return new Fragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
