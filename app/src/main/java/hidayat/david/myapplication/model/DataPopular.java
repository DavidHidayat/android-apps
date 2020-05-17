package hidayat.david.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Data implements Parcelable {
    int vote_count,id;
    boolean video,adult;
    float vote_average,popularity;
    String title,poster_path,original_language,original_title,backdrop_path,overview,release_date;
    List<Integer> genre_ids;
    public  Data(){}
    public Data(Parcel in) {
        vote_count = in.readInt();
        id = in.readInt();
        video = in.readByte() != 0;
        adult = in.readByte() != 0;
        vote_average = in.readFloat();
        popularity = in.readFloat();
        title = in.readString();
        poster_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(vote_count);
        parcel.writeInt(id);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeFloat(vote_average);
        parcel.writeFloat(popularity);
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(original_language);
        parcel.writeString(original_title);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);

    }
}
