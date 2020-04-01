package ood.controller;

import ood.model.User;
import ood.model.Voting;
import ood.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;


@RestController
public class VotingController {
    @Autowired
    VotingService votingService;

    @RequestMapping(value = "/voting/{votingId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting getVotingById(@PathVariable(name = "votingId") long votingId){
        return votingService.getVotingById(votingId);
    }

    @RequestMapping(value = "/voting",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting save(@RequestBody Voting voting){
        return votingService.save(voting);
    }

    @RequestMapping(value = "/voting",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting update(@RequestBody Voting voting){
        return votingService.update(voting);
    }

    @RequestMapping(value = "/voting",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@RequestBody Voting voting){
        return votingService.delete(voting);
    }

    @RequestMapping(value = "/voting/startTime",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting changeStartTime(@RequestBody Voting voting, OffsetDateTime startTime){
        return votingService.changeStartTime(voting,startTime);
    }

    @RequestMapping(value = "/voting/endTime",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting changeEndTime(@RequestBody Voting voting, OffsetDateTime endTime){
        return votingService.changeEndTime(voting,endTime);
    }

    @RequestMapping(value = "/voting/movie",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting voteForMovie(@RequestBody User user, Voting voting, String ttId){
        return votingService.voteForMovie(user,voting,ttId);
    }

}
