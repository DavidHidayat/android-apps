package hidayat.david.myapplication.ui.gallery;

import android.content.Context;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hidayat.david.myapplication.R;
import hidayat.david.myapplication.adapter.DataAdapter;
import hidayat.david.myapplication.adapter.DataAdapterPopular;
import hidayat.david.myapplication.model.Data;
import hidayat.david.myapplication.model.DataPopular;
import hidayat.david.myapplication.viewModel.DataPopularViewModel;
import hidayat.david.myapplication.viewModel.DataViewModel;

public class GalleryFragment extends Fragment {

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
//                showLoading(false);
            }
        }
    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_data = view.findViewById(R.id.recyclerView);
        adapter = new DataAdapterPopular(getContext());
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rv_data.setLayoutManager(layoutManager);
        rv_data.setAdapter(adapter);
        adapter.setOnItemClickCallback(new DataAdapterPopular.OnItemClickCallback() {
            @Override
            public void onItemClicked(Data data) {
//                showSelectedMovie(data);
            }
        });

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        context = getContext();

        return root;
    }
}
