package core;

import java.util.List;

public class MovieSearchResponse {
    int page;
    int total_results;
    List<PossibleMatch> results;
    int total_pages;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Page: ");
        s.append(page);
        s.append(" of ");
        s.append(total_pages + "\n");
        s.append("Total results: " + total_results + "\n");
        s.append(results.get(1).original_title);

        return s.toString();
    }

}

class PossibleMatch {
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
}