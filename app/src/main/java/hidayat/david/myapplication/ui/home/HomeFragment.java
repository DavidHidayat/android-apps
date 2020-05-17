package hidayat.david.myapplication.ui.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import hidayat.david.myapplication.R;
import hidayat.david.myapplication.adapter.DataAdapter;
import hidayat.david.myapplication.adapter.SliderAdapter;
import hidayat.david.myapplication.model.Data;
import hidayat.david.myapplication.viewModel.DataViewModel;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager viewPager;
    private TabLayout indicator;

    List<Integer> color;
    List<String> colorName;
    List<String> imageUrl;
    Context context;

    private RecyclerView rv_data;

    private DataAdapter adapter;
//    private ProgressBar progressBar;
    private DataViewModel dataViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);
        dataViewModel.getItem().observe(getActivity(),getData);
        dataViewModel.setItem(null);
    }
    private Observer<ArrayList<Data>> getData = new Observer<ArrayList<Data>>() {
        @Override
        public void onChanged(ArrayList<Data> items) {
            if (items != null) {
                adapter.setData(items);
//                showLoading(false);
            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_data = view.findViewById(R.id.recyclerView);
        adapter = new DataAdapter(getContext());
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_data.setLayoutManager(layoutManager);
        rv_data.setAdapter(adapter);
        adapter.setOnItemClickCallback(new DataAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Data data) {
//                showSelectedMovie(data);
            }
        });

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        viewPager=(ViewPager)root.findViewById(R.id.viewPager);
        indicator=(TabLayout)root.findViewById(R.id.indicator);
        color = new ArrayList<>();
        color.add(Color.RED);
        color.add(Color.GREEN);
        color.add(Color.BLUE);

        colorName = new ArrayList<>();
        colorName.add("GUNUNG");
        colorName.add("KOTA");
        colorName.add("PANTAI");

        imageUrl = new ArrayList<>();
        imageUrl.add("https://www.goodnewsfromindonesia.id/uploads/post/large-gunung-kerinci-a05e1da851e221b71264ba26b705bafd.jpg");
        imageUrl.add("https://pixnio.com/free-images/2017/10/27/2017-10-27-06-33-20-1100x825.jpg");
        imageUrl.add("https://i0.wp.com/www.simphortour.com/wp-content/uploads/2019/03/pemandangan-di-bibir-pantai.jpg?fit=1226%2C762&ssl=1");

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
