package ood.controller;

import com.fasterxml.jackson.annotation.JsonView;
import ood.model.*;
import ood.service.EventService;
import ood.service.GroupService;
import ood.service.UserService;
import ood.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;


@RestController
public class VotingController {
    @Autowired
    VotingService votingService;
    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    EventService eventService;

    @JsonView(View.Voting.class)
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

    @JsonView(View.Voting.class)
    @RequestMapping(value = "/voting/creation",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting createVoting(@RequestParam Date startTime, @RequestParam Date endTime,@RequestParam long eventId,@RequestParam String groupName) throws ParseException {
        Group group = groupService.getGroupByName(groupName);
        Event event = eventService.getEventById(eventId);
        return votingService.createVoting(startTime.toInstant().atOffset(ZoneOffset.UTC), endTime.toInstant().atOffset(ZoneOffset.UTC),event,group);
    }

    @JsonView(View.Voting.class)
    @RequestMapping(value = "/voting/startTime",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting changeStartTime(@RequestParam long votingId, @RequestParam Date startTime){
        return votingService.changeStartTime(votingService.getVotingById(votingId),startTime.toInstant().atOffset(ZoneOffset.UTC));
    }

    @JsonView(View.Voting.class)
    @RequestMapping(value = "/voting/endTime",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting changeEndTime(@RequestParam long votingId, @RequestParam Date endTime){
        return votingService.changeEndTime(votingService.getVotingById(votingId),endTime.toInstant().atOffset(ZoneOffset.UTC));
    }

    @JsonView(View.Voting.class)
    @RequestMapping(value = "/voting/movie",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Voting voteForMovie(@RequestParam String userName, @RequestParam long votingId, @RequestParam String ttId){
        return votingService.voteForMovie(userService.getUserByName(userName),votingService.getVotingById(votingId),ttId);
    }

}
