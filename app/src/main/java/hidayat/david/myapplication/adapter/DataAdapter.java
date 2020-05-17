package hidayat.david.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import hidayat.david.myapplication.BuildConfig;
import hidayat.david.myapplication.R;
import hidayat.david.myapplication.model.Data;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.HolderData> {
    private List<Data> DataList ;
    private LayoutInflater mInflater;
    private Context mContext;
    private String overview;
    private OnItemClickCallback onItemClickCallback;


    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public DataAdapter(Context context)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.DataList = new ArrayList<>();
    }
    public void setData(List<Data> dataList)
    {
        this.DataList= dataList ;
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
        final Data data = DataList.get(position);
        Glide.with(mContext)
                .load(BuildConfig.IMAGE_PATH+data.getPoster_path())
                .into(holder.imgBrand);
        holder.textView.setText(data.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(DataList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (DataList == null) ? 0 : DataList.size();
    }
    public interface OnItemClickCallback {
        void onItemClicked(Data data);
    }
    public class HolderData extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imgBrand;
        CardView container;
        public HolderData(View itemView) {
            super(itemView);
//            container       = itemView.findViewById(R.id.container_item);
            textView        = itemView.findViewById(R.id.textView);
            imgBrand        = itemView.findViewById(R.id.img_brand);
        }
    }

}
