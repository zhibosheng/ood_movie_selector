package ood.service;


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

    public URL getMovie(String ttId){
        URL url = null;
        try{
            HttpResponse<String> response = Unirest.get("https://imdb8.p.rapidapi.com/title/get-videos?limit=25&region=US&tconst="+ttId)
                    .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                    .header("x-rapidapi-key", "21fb4432b2msh936d28e4bc4b053p137a56jsn3fa00555b0d1")
                    .asString();
            System.out.println(response.getBody());
            JSONObject jsonObject = new JSONObject(response.getBody());

            JSONObject resource = new JSONObject(jsonObject.get("resource").toString());


            JSONArray array = (JSONArray) resource.get("videos");
//            System.out.println(array.get(0));
            JSONObject video = new JSONObject(array.get(0).toString());
            String videoId = video.get("id").toString();
            String[] viArr = videoId.split("/");
            String vi = viArr[2];
            System.out.println(vi);
            url = new URL("https://www.imdb.com/video/"+vi);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return url;
    }


}
