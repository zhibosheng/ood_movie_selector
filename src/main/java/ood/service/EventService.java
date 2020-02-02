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

    public Event save(Event event){
        return eventDao.save(event);
    }

    public Event update(Event event){
        return eventDao.update(event);
    }

    public boolean delete(Event event){
        return eventDao.delete(event);
    }

    public Event createEvent(Group group, OffsetDateTime createTime, OffsetDateTime showTime){
        Event event = new Event();
        event.setGroup(group);
        event.setCreateTime(createTime);
        event.setShowTime(showTime);
        return eventDao.save(event);
    }

    public Event changeShowTime(Event event,OffsetDateTime showTime){
        event.setShowTime(showTime);
        eventDao.update(event);
    }
}
