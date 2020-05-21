package hidayat.david.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import hidayat.david.myapplication.BuildConfig;
import hidayat.david.myapplication.R;
import hidayat.david.myapplication.api.ApiRequest;
import hidayat.david.myapplication.api.RetroServer;
import hidayat.david.myapplication.model.Data;
import hidayat.david.myapplication.model.DataList;
import hidayat.david.myapplication.model.DataListPopular;
import hidayat.david.myapplication.model.DataPopular;
import hidayat.david.myapplication.viewModel.DataViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAdapterPopular extends RecyclerView.Adapter<DataAdapterPopular.HolderData> {
    private List<DataPopular> DataListPopularAdapter ;
    private LayoutInflater mInflater;
    private Context mContext;
    private OnItemClickCallback onItemClickCallback;
    private ApiRequest api;
    int page = 2;
    int fragmentID;

    String TAG ="DataAdapter";


    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public DataAdapterPopular(Context context,int fragmentID)
    {
        this.fragmentID = fragmentID;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.DataListPopularAdapter = new ArrayList<>();
    }
    public void setData(List<DataPopular> dataList)
    {
        this.DataListPopularAdapter= dataList ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataAdapterPopular.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rv_gallery,parent,false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull final DataAdapterPopular.HolderData holder, int position) {
        final DataPopular data = DataListPopularAdapter.get(position);

        if (position == DataListPopularAdapter.size()-9 && fragmentID == R.id.nav_gallery) {
            loadNewData(page);
            page++;
        }
        Glide.with(mContext)
                .load(BuildConfig.IMAGE_PATH+data.getPoster_path())
                .into(holder.imgBrand);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(DataListPopularAdapter.get(holder.getAdapterPosition()));
            }
        });
    }
    public interface OnItemClickCallback {
        void onItemClicked(DataPopular data);
    }
    public void loadNewData(int page){
        api = RetroServer.getClient().create(ApiRequest.class);
        final ArrayList<DataPopular> listItems = new ArrayList<>();
        Call<DataListPopular> GetData = api.getMoviePopularPage(BuildConfig.API_KEY,page);
        GetData.enqueue(new Callback<DataListPopular>() {
            @Override
            public void onResponse(Call<DataListPopular> call, Response<DataListPopular> response) {
                List<DataPopular> dp= response.body().getResults();
                for (int i = 0; i < dp.size(); i++) {
                    listItems.add(dp.get(i));
                }
                DataListPopularAdapter.addAll(listItems);
                notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<DataListPopular> call, Throwable t) {
                Toast.makeText(mContext, mContext.getString(R.string.problem_connection), Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return (DataListPopularAdapter == null) ? 0 : DataListPopularAdapter.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ImageView imgBrand;
        public HolderData(View itemView) {
            super(itemView);
            imgBrand        = itemView.findViewById(R.id.img_brand);
        }
    }

}
