package ood.repository;


import ood.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl implements EventDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Event save(Event event){
        return event;
    }

    @Override
    public Event update(Event event){
        return event;
    }

    @Override
    public boolean delete(Event event) {
        return false;
    }
}
