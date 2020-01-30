package ood.service;

import ood.model.Event;
import ood.repository.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventDao dao;

    public Event save(Event event){
        return dao.save(event);
    }

    public Event update(Event event){
        return dao.update(event);
    }

    public boolean delete(Event event){
        return dao.delete(event);
    }
}
