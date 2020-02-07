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
