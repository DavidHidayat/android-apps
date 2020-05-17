package hidayat.david.myapplication.viewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hidayat.david.myapplication.BuildConfig;
import hidayat.david.myapplication.R;
import hidayat.david.myapplication.api.ApiRequest;
import hidayat.david.myapplication.api.RetroServer;
import hidayat.david.myapplication.model.Data;
import hidayat.david.myapplication.model.DataList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends AndroidViewModel {
    private ApiRequest api;
    private MutableLiveData<ArrayList<Data>> listItem = new MutableLiveData<>();

    public DataViewModel(@NonNull Application application) {
        super(application);
    }
    public void setItem(String query ) {
        if(query == null){
            api = RetroServer.getClient().create(ApiRequest.class);
            final ArrayList<Data> listItems = new ArrayList<>();
            Call<DataList> GetData = api.getMoviePopular(BuildConfig.API_KEY);
            GetData.enqueue(new Callback<DataList>() {
                @Override
                public void onResponse(Call<DataList> call, Response<DataList> response) {
                    List<Data> DataList= response.body().getResults();
                    for (int i = 0; i < DataList.size(); i++) {
                        listItems.add(DataList.get(i));
                    }
                    listItem.postValue(listItems);
                }
                @Override
                public void onFailure(Call<DataList> call, Throwable t) {
                    Toast.makeText(getApplication(), getApplication().getString(R.string.problem_connection), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            api = RetroServer.getClient().create(ApiRequest.class);
            final ArrayList<Data> listItems = new ArrayList<>();
            Call<DataList> GetData = api.getMovieSearch(query, Locale.getDefault().toString(),BuildConfig.API_KEY);
            GetData.enqueue(new Callback<DataList>() {
                @Override
                public void onResponse(Call<DataList> call, Response<DataList> response) {
                    List<Data> DataList= response.body().getResults();
                    for (int i = 0; i < DataList.size(); i++) {
                        listItems.add(DataList.get(i));
                    }
                    listItem.postValue(listItems);
                }
                @Override
                public void onFailure(Call<DataList> call, Throwable t) {
                    Toast.makeText(getApplication(), getApplication().getString(R.string.problem_connection), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    public MutableLiveData<ArrayList<Data>> getItem() {
        return listItem;
    }

}
