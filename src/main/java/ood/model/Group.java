package ood.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private long groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_description")
    private String groupDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id")
    private User moderator;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_event_id",referencedColumnName = "event_id")
    private Event lastEvent;

    @ManyToMany(mappedBy = "joinGroups",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Event> events = new HashSet<>();



    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public User getModerator() {
        try{
            boolean flag = moderator.equals(null);
        } catch (Exception e){
            return null;
        }
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

    public Event getLastEvent() {
        try{
            boolean flag = lastEvent.equals(null);
        } catch (Exception e){
            return null;
        }
        return lastEvent;
    }

    public void setLastEvent(Event lastEvent) {
        this.lastEvent = lastEvent;
    }

    public Set<User> getUsers() {
        try{
            int size = users.size();
        }
        catch (Exception e){
            return null;
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Event> getEvents() {
        try{
            int size = events.size();
        }
        catch (Exception e){
            return null;
        }
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
