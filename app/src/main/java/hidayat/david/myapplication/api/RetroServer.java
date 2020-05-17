package hidayat.david.myapplication.api;

import android.util.Log;

import hidayat.david.myapplication.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static String baseURL = BuildConfig.BASE_URL;
    private static String TAG = "RetroServer";

    private  static Retrofit retrofit;

    public static  Retrofit getClient(){
        if(retrofit==null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor).build();
            retrofit  = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Log.e(TAG,""+retrofit);
        return retrofit;
    }

}
