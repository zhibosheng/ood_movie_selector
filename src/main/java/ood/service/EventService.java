package ood.service;

import ood.model.Event;
import ood.model.Group;
import ood.repository.EventDao;
import ood.repository.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventDao eventDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupService groupService;

    @Autowired
    private MovieAPIService movieAPIService;

    public Event save(Event event){
        return eventDao.save(event);
    }

    public Event update(Event event){
        return eventDao.update(event);
    }

    public boolean delete(Event event){
        return eventDao.delete(event);
    }

    public Event getEventById(long eventId){
        return eventDao.getEventById(eventId);
    }

    public List<Event> getEventByShowTime(OffsetDateTime showTime) { return eventDao.getEventByShowTime(showTime); }

    public Event getEventWithVoting(long eventId){
        return eventDao.getEventWithVoting(eventId);
    }

    public Event getEventWithGroup(long eventId){
        return eventDao.getEventWithGroup(eventId);
    }

    public Event createEvent(Group group, OffsetDateTime showTime){
        Date date = new Date();
        OffsetDateTime createTime = date.toInstant().atOffset(ZoneOffset.UTC);
        return createEvent(group,createTime,showTime);
    }

    public Event createEvent(Group group, OffsetDateTime createTime, OffsetDateTime showTime){
        Event event = new Event();
        event.setGroup(group);
        event.setCreateTime(createTime);
        event.setShowTime(showTime);
        groupService.sendStartEventEmail(group,event);
        event = eventDao.save(event);
        group.setLastEvent(event);
        groupDao.update(group);
        return event;
    }

//    public Event selectMovies(Event event,List<String> movieArray){
//        String movieArrayString = "";
//        for(String ele:movieArray){
//            movieArrayString += ele + ",";
//        }
//        movieArrayString = movieArrayString.substring(0,movieArrayString.length()-1);
//        event.setSelectedMovies(movieArrayString);
//        return eventDao.update(event);
//    }

    public Event changeShowTime(Event event,OffsetDateTime showTime){
        event.setShowTime(showTime);
        return eventDao.update(event);
    }
}
