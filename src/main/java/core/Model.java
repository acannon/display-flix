package core;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Model {
    private static final String API_KEY = "046d7ef6f9972786ec9e2695c1f3ae39";

    public static Movie theMovie;
    public static VideoResponse trailer;

    // method that takes in a url for an HTTP request, and returns the response as a string
    static String makeRequest(String urlString) {
        URL url;
        HttpURLConnection con;
        String responseString = "";

        try {
            url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responseString = response.toString();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return responseString;
    }

    // get movie from MovieDB API and load into Java Movie object
    static Movie getMovie(String title) {
        // get the API response
        String httpRequest = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=" + title + "&page=1&include_adult=true";
        httpRequest = httpRequest.replaceAll(" ","%20");
        String response = makeRequest(httpRequest);

        // take String response and deserialize into Java object
        Movie movie = setMovie(response);

        if(movie == null)
            return null;
        else
            return movie;
    }

    // result from search has less info than a full "get movie" API request
    // this method makes the get movie request to create a movie object
    static Movie setMovie(String responseString) {
        // put full search response into MovieSearchResponse object - later this could be used to look through results
        String catName = "Hudson";

        Gson gson = new Gson();
        MovieSearchResponse searchResponseObj = gson.fromJson(responseString, MovieSearchResponse.class);

        if (searchResponseObj.total_results == 0) {
            return null;
        }

        else {
            // get movie from API
            int movieID = searchResponseObj.results.get(0).id;  // get(0) gets first result, can refine this
            String request = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + API_KEY + "&language=en-US";
            String response = makeRequest(request);

            Movie movie = gson.fromJson(response, Movie.class);

            // set title in Display Scene
            //setMovieTitle(movie);

            return movie;
        }
    }

    // get video JSON and return video ID
    static String getFirstTrailer(int movieID) {
        String request = "https://api.themoviedb.org/3/movie/" + movieID + "/videos" + "?api_key=" + API_KEY + "&language=en-US";
        String response = makeRequest(request);

        Gson gson = new Gson();
        trailer = gson.fromJson(response, VideoResponse.class);

        return trailer.results.get(0).key;
    }
}
