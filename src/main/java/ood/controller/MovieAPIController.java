package ood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MovieAPIController {

    @Autowired
    private MovieAPIController movieAPIController;

    @RequestMapping(value = "/movie/detail/{ttId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap getOverviewDetails(@PathVariable(name = "ttId") String ttId){
        return movieAPIController.getOverviewDetails(ttId);
    }

    @RequestMapping(value = "/movie/review/{ttId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<HashMap> getUserReviewsList(@PathVariable(name = "ttId") String ttId){
        return movieAPIController.getUserReviewsList(ttId);
    }

    @RequestMapping(value = "/movie/trailerUrl/{ttId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public URL getTrailerUrl(@PathVariable(name = "ttId") String ttId){
        return movieAPIController.getTrailerUrl(ttId);
    }
}
