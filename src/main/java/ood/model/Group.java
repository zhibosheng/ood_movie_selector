package ood.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id")
    private User moderator;

    @OneToOne
    @JoinColumn(name = "last_event_id",referencedColumnName = "event_id")
    private Event lastEvent;

    @ManyToMany(mappedBy = "joinGroups")
    private List<User> users;

    @OneToMany(mappedBy = "grouEventsp", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Event> events;



    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

    public Event getLastEvent() {
        return lastEvent;
    }

    public void setLastEvent(Event lastEvent) {
        this.lastEvent = lastEvent;
    }

    public List<User> getUsers() {
        try{
            int size = users.size();
        }
        catch (Exception e){
            return null;
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Event> getEvents() {
        try{
            int size = events.size();
        }
        catch (Exception e){
            return null;
        }
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
