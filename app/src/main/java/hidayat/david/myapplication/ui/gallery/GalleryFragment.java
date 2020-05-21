package hidayat.david.myapplication.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hidayat.david.myapplication.MainActivity;
import hidayat.david.myapplication.R;
import hidayat.david.myapplication.adapter.DataAdapter;
import hidayat.david.myapplication.adapter.DataAdapterPopular;
import hidayat.david.myapplication.model.Data;
import hidayat.david.myapplication.model.DataPopular;
import hidayat.david.myapplication.ui.detail.DetailMovieFragment;
import hidayat.david.myapplication.viewModel.DataPopularViewModel;
import hidayat.david.myapplication.viewModel.DataViewModel;

public class GalleryFragment extends Fragment {
    public static final String EXTRA_DATA = "data_detail";
    public static final String TAG = "GalleryFragment";

    private DataPopularViewModel dataViewModel;
    private DataAdapterPopular adapter;
    private RecyclerView rv_data;
    Context context;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataViewModel = ViewModelProviders.of(getActivity()).get(DataPopularViewModel.class);
        dataViewModel.getItem().observe(getActivity(),getData);
        dataViewModel.setItem(null);
    }
    private Observer<ArrayList<DataPopular>> getData = new Observer<ArrayList<DataPopular>>() {
        @Override
        public void onChanged(ArrayList<DataPopular> items) {
        if (items != null) {
            adapter.setData(items);
        }
        }
    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_data = view.findViewById(R.id.recyclerView);
        adapter = new DataAdapterPopular(getContext(),R.id.nav_gallery);
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rv_data.setLayoutManager(layoutManager);
        rv_data.setAdapter(adapter);
        adapter.setOnItemClickCallback(new DataAdapterPopular.OnItemClickCallback() {
            @Override
            public void onItemClicked(DataPopular data) {
                showSelectedData(data);
            }
        });
    }

    private void showSelectedData(DataPopular data) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_DATA,data);
        ((MainActivity)getActivity()).displaySelectedScreen(212,args);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        context = getContext();
        root.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d(TAG, String.valueOf(keyCode));
                return false;
            }
        });
        return root;
    }
}
