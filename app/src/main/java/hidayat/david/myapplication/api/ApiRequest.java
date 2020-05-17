package hidayat.david.myapplication.api;

import hidayat.david.myapplication.model.DataList;
import hidayat.david.myapplication.model.DataListPopular;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("/3/movie/popular")
    Call<DataList> getMoviePopular(@Query("api_key") String apiKey,@Query("page") int page);
    @GET("/3/movie/popular")
    Call<DataListPopular> getMoviePopularPage(@Query("api_key") String apiKey, @Query("page") int page);
    @GET("3/search/movie")
    Call<DataList> getMovieSearch (
            @Query("query") String keyword,
            @Query("language") String language,
            @Query("api_key") String apiKey);
    @GET("3/discover/movie")
    Call<DataList> getReleaseMovie (
            @Query("api_key") String keyword,
            @Query("primary_release_date.gte") String start_date,
            @Query("primary_release_date.lte") String finish_date);
}
