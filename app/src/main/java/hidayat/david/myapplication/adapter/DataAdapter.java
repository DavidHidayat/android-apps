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
import hidayat.david.myapplication.viewModel.DataViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.HolderData> {
    private List<Data> DataListAdapter ;
    private LayoutInflater mInflater;
    private Context mContext;
    private String overview;
    private OnItemClickCallback onItemClickCallback;

    String TAG ="DataAdapter";


    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public DataAdapter(Context context)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.DataListAdapter = new ArrayList<>();
    }
    public void setData(List<Data> dataList)
    {
        this.DataListAdapter= dataList ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rv_home,parent,false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull final DataAdapter.HolderData holder, int position) {
        final Data data = DataListAdapter.get(position);
        Log.d(TAG,"postitio = "+position);
        Log.d(TAG,"DataList = "+DataListAdapter.size());
        Glide.with(mContext)
                .load(BuildConfig.IMAGE_PATH+data.getPoster_path())
                .into(holder.imgBrand);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(DataListAdapter.get(holder.getAdapterPosition()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return (DataListAdapter == null) ? 0 : DataListAdapter.size();
    }
    public interface OnItemClickCallback {
        void onItemClicked(Data data);
    }
    public class HolderData extends RecyclerView.ViewHolder {
        ImageView imgBrand;
        CardView container;
        public HolderData(View itemView) {
            super(itemView);
            imgBrand        = itemView.findViewById(R.id.img_brand);
        }
    }

}
