package ood.service;

import ood.ApplicationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class MovieAPIServiceTest {
    @Autowired
    MovieAPIService movieAPIService;
    @Test
    public void getVideoUrl(){
        String ttId = "tt0944947";
        URL url = movieAPIService.getMovie(ttId);
        Assert.assertNotNull(url);
    }
}
