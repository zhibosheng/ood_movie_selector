package ood.controller;

import ood.model.Event;
import ood.model.Group;
import ood.service.EventService;
import ood.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@RestController
public class EventController {
    @Autowired
    EventService eventService;
    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/event/{eventId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event getEventById(@PathVariable(name = "eventId")  long eventId){
        return eventService.getEventById(eventId);
    }

    @RequestMapping(value = "/event/group/{eventId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event getEventWithGroup(@PathVariable(name = "eventId") long eventId){
        return eventService.getEventWithGroup(eventId);
    }

    @RequestMapping(value = "/event/voting/{eventId}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event getEventWithVoting(@PathVariable(name = "eventId") long eventId){
        return eventService.getEventWithVoting(eventId);
    }

    @RequestMapping(value = "/event",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event save(@RequestBody Event event){
        return eventService.save(event);
    }

    @RequestMapping(value = "/event",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event update(@RequestBody Event event){
        return eventService.update(event);
    }

    @RequestMapping(value = "/event",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@RequestBody Event event){
        return eventService.delete(event);
    }

    @RequestMapping(value = "/event/creation",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event createEvent(@RequestParam String groupName, @RequestParam Date showTime){
        return eventService.createEvent(groupService.getGroupByName(groupName),showTime.toInstant().atOffset(ZoneOffset.UTC));
    }

    @RequestMapping(value = "/event/showtime",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event changeShowTime(@RequestParam long eventId, @RequestParam Date showTime){
        return eventService.changeShowTime(eventService.getEventById(eventId),showTime.toInstant().atOffset(ZoneOffset.UTC));
    }
}
