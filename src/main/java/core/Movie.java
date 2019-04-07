package core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Movie {
    int vote_count;
    int id;
    boolean video;
    float vote_average;
    String title;
    float popularity;
    String poster_path;
    String original_language;
    String original_title;
    int[] genre_ids;
    String backdrop_path;
    boolean adult;
    String overview;
    String release_date;
    String trailer;
    String tagline;



    String getTrailerLink(int id) {
        String videoID = Model.getFirstTrailer(id);
        return "https://www.themoviedb.org/movie/" + id +"#play=" + videoID;
    }
}
