package ood.service;

import ood.model.Event;
import ood.model.Group;
import ood.repository.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class EventService {
    @Autowired
    private EventDao eventDao;

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

//    public Event getEventByShowTime(OffsetDateTime showTime) { return eventDao.getEventByShowTime(showTime); }

    public Event getEventWithVoting(long eventId){
        return eventDao.getEventWithVoting(eventId);
    }

    public Event createEvent(Group group, OffsetDateTime createTime, OffsetDateTime showTime){
        Event event = new Event();
        event.setGroup(group);
        event.setCreateTime(createTime);
        event.setShowTime(showTime);
        groupService.sendStartEventEmail(group,event);
        return eventDao.save(event);
    }

    public Event changeShowTime(Event event,OffsetDateTime showTime){
        event.setShowTime(showTime);
        return eventDao.update(event);
    }
}
