package ood.controller;

import ood.service.MovieAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

@RestController
public class MovieAPIController {

    @Autowired
    private MovieAPIService movieAPIService;

    @RequestMapping(value = "/movie/default",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap getDefaultMovies(){
        return movieAPIService.getDefaultMovies();
    }

    @RequestMapping(value = "/movie/find/{movieName}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap findMovie(@PathVariable(name = "movieName") String movieName){
        HashMap ttId = new HashMap();
        ttId.put("ttId",movieAPIService.findMovie(movieName));
        return ttId;
//        return movieAPIService.findMovie(movieName);
    }

    @RequestMapping(value = "/movie/moreLikeThis/{ttId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> getMoreLikeThis(@PathVariable(name = "ttId") String ttId){
        return movieAPIService.getMoreLikeThis(ttId);
    }

    @RequestMapping(value = "/movie/detail/{ttId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap getOverviewDetails(@PathVariable(name = "ttId") String ttId){
        return movieAPIService.getOverviewDetails(ttId);
    }

    @RequestMapping(value = "/movie/review/{ttId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<HashMap> getUserReviewsList(@PathVariable(name = "ttId") String ttId){
        return movieAPIService.getUserReviewsList(ttId);
    }

    @RequestMapping(value = "/movie/trailerUrl/{ttId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap getTrailerUrl(@PathVariable(name = "ttId") String ttId){
        HashMap trailerUrl = new HashMap();
        trailerUrl.put("url",movieAPIService.getTrailerUrl(ttId));
        return trailerUrl;
//        return movieAPIService.getTrailerUrl(ttId);
    }
}
