package ood.controller;

import ood.model.User;
import ood.model.Voting;
import ood.service.UserService;
import ood.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;


@RestController
public class VotingController {
    @Autowired
    VotingService votingService;
    @Autowired
    UserService userService;

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
    public Voting changeStartTime(@RequestParam long votingId, @RequestParam OffsetDateTime startTime){
        return votingService.changeStartTime(votingService.getVotingById(votingId),startTime);
    }

    @RequestMapping(value = "/voting/endTime",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting changeEndTime(@RequestParam long votingId, @RequestParam OffsetDateTime endTime){
        return votingService.changeEndTime(votingService.getVotingById(votingId),endTime);
    }

    @RequestMapping(value = "/voting/movie",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting voteForMovie(@RequestParam String userName, @RequestParam long votingId, @RequestParam String ttId){
        return votingService.voteForMovie(userService.getUserByName(userName),votingService.getVotingById(votingId),ttId);
    }

}
