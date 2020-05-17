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
import hidayat.david.myapplication.model.DataListPopular;
import hidayat.david.myapplication.model.DataPopular;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPopularViewModel extends AndroidViewModel {
    private ApiRequest api;
    private MutableLiveData<ArrayList<DataPopular>> listItem = new MutableLiveData<>();

    public DataPopularViewModel(@NonNull Application application) {
        super(application);
    }
    public void setItem(String query ) {
        if(query == null){
            api = RetroServer.getClient().create(ApiRequest.class);
            final ArrayList<DataPopular> listItems = new ArrayList<>();
            Call<DataListPopular> GetData = api.getMoviePopularPage(BuildConfig.API_KEY,1);
            GetData.enqueue(new Callback<DataListPopular>() {
                @Override
                public void onResponse(Call<DataListPopular> call, Response<DataListPopular> response) {
                    List<DataPopular> DataList= response.body().getResults();
                    for (int i = 0; i < DataList.size(); i++) {
                        listItems.add(DataList.get(i));
                    }
                    listItem.postValue(listItems);
                }
                @Override
                public void onFailure(Call<DataListPopular> call, Throwable t) {
                    Toast.makeText(getApplication(), getApplication().getString(R.string.problem_connection), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    public MutableLiveData<ArrayList<DataPopular>> getItem() {
        return listItem;
    }

}
