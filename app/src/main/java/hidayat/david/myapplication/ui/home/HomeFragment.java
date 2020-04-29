package hidayat.david.myapplication.ui.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import hidayat.david.myapplication.R;
import hidayat.david.myapplication.adapter.SliderAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager viewPager;
    private TabLayout indicator;

    List<Integer> color;
    List<String> colorName;
    List<String> imageUrl;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        context = getContext();
        viewPager=(ViewPager)root.findViewById(R.id.viewPager);
        indicator=(TabLayout)root.findViewById(R.id.indicator);
        color = new ArrayList<>();
        color.add(Color.RED);
        color.add(Color.GREEN);
        color.add(Color.BLUE);

        colorName = new ArrayList<>();
        colorName.add("KOTA");
        colorName.add("PANTAI");
        colorName.add("GUNUNG");

        imageUrl = new ArrayList<>();
        imageUrl.add("https://pixnio.com/free-images/2017/10/27/2017-10-27-06-33-20-1100x825.jpg");
        imageUrl.add("https://i0.wp.com/www.simphortour.com/wp-content/uploads/2019/03/pemandangan-di-bibir-pantai.jpg?fit=1226%2C762&ssl=1");
        imageUrl.add("https://petualang.travelingyuk.com/unggah/2019/02/fori_badria_aswianto_68198954_5a8ad7ff8cb6f5_1968.jpg");

        viewPager.setAdapter(new SliderAdapter(context, color, colorName,imageUrl));
        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        return root;
    }
    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if(getActivity()!=null){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < color.size() - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }

}
