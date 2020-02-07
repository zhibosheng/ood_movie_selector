package ood.service;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieAPIService {

//    public static void getMovie(){
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://imdb8.p.rapidapi.com/title/get-videos?limit=25&region=US&tconst=tt0944947")
//                .get()
//                .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", "21fb4432b2msh936d28e4bc4b053p137a56jsn3fa00555b0d1")
//                .build();
//
//
//        try{
//            Response response = client.newCall(request).execute();
//            System.out.println(response.body());
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }

    public URL getTrailerUrl(String ttId){
        URL url = null;
        try{
            HttpResponse<String> response = Unirest.get("https://imdb8.p.rapidapi.com/title/get-videos?limit=1&region=US&tconst="+ttId)
                    .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                    .header("x-rapidapi-key", "21fb4432b2msh936d28e4bc4b053p137a56jsn3fa00555b0d1")
                    .asString();
//            System.out.println(response.getBody());
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject resource = (JSONObject) jsonObject.get("resource");
            JSONArray array = (JSONArray) resource.get("videos");
//            System.out.println(array.get(0));
            JSONObject video = (JSONObject)array.get(0);
            String videoId = video.get("id").toString();
            String[] viArr = videoId.split("/");
            String vi = viArr[2];
//            System.out.println(vi);
            url = new URL("https://www.imdb.com/video/"+vi);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return url;
    }

    public List<HashMap> getUserReviewsList(String ttId){
        List<HashMap> userReviewsList = new ArrayList<HashMap>();
        try{
            HttpResponse<String> response = Unirest.get("https://imdb8.p.rapidapi.com/title/get-user-reviews?tconst="+ttId)
                    .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                    .header("x-rapidapi-key", "21fb4432b2msh936d28e4bc4b053p137a56jsn3fa00555b0d1")
                    .asString();
//            System.out.println(response.getBody());
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray array = (JSONArray) jsonObject.get("reviews");
            for (int i=0; i<array.length();i++){
                HashMap<String, String> userReview  = new HashMap<>();
                JSONObject arr = (JSONObject) array.get(i);
                JSONObject author = (JSONObject) arr.get("author");
                String displayName = author.get("displayName").toString();
                String reviewTitle = arr.get("reviewTitle").toString();
                String reviewText = arr.get("reviewText").toString();
                String submissionDate = arr.get("submissionDate").toString();
                userReview.put("displayName",displayName);
                userReview.put("reviewTitle",reviewTitle);
                userReview.put("reviewText",reviewText);
                userReview.put("submissionDate",submissionDate);
                userReviewsList.add(userReview);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return userReviewsList;
    }

    public String getRating(String ttId){
        String rating = "";
        try{
            HttpResponse<String> response = Unirest.get("https://imdb8.p.rapidapi.com/title/get-ratings?tconst="+ttId)
                    .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                    .header("x-rapidapi-key", "21fb4432b2msh936d28e4bc4b053p137a56jsn3fa00555b0d1")
                    .asString();
//            System.out.println(response.getBody());
            JSONObject jsonObject = new JSONObject(response.getBody());
            rating = jsonObject.get("rating").toString();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return rating;
    }

}
