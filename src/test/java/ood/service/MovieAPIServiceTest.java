package ood.service;

import ood.ApplicationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class MovieAPIServiceTest {
    @Autowired
    MovieAPIService movieAPIService;
    String ttId = "tt0848228";

    @Test
    public void findMovie(){
        String ttId=  movieAPIService.findMovie("The Lord of the Rings: The Return of the King");
        System.out.println("The movie id is: "+ttId);
        Assert.assertNotNull(ttId);
    }


    @Test
    public void getMoreLikeThis(){
        List<String> moreLikeThisList =  movieAPIService.getMoreLikeThis(ttId);
        System.out.println(moreLikeThisList);
        Assert.assertNotNull(moreLikeThisList);
    }


    @Test
    public void getOverviewDetails(){
        //HashMap<String,Object> overviewDetails= movieAPIService.getOverviewDetails(ttId);
        HashMap<String,Object> overviewDetails= movieAPIService.getOverviewDetails("tt0167260");
        System.out.println(overviewDetails);
        System.out.println(overviewDetails.get("genres"));
        Assert.assertNotNull(overviewDetails);
    }

    @Test
    public void getVideoUrl(){
        URL url = movieAPIService.getTrailerUrl(ttId);
        System.out.println(url);
        Assert.assertNotNull(url);
    }

    @Test
    public void getUserReviewsList(){
        List<HashMap>  userReviewsList = movieAPIService.getUserReviewsList(ttId);
        Assert.assertNotNull(userReviewsList);
    }

    @Test
    public void getRating(){
        String rating= movieAPIService.getRating(ttId);
        Assert.assertNotNull(rating);
    }
}
