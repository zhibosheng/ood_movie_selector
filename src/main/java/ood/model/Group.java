package ood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "groups")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    @JsonView({View.Event.class, View.Group.class,View.User.class})
    private long groupId;

    @Column(name = "group_name")
    @JsonView({View.Event.class, View.Group.class,View.User.class})
    private String groupName;

    @Column(name = "group_description")
    @JsonView({View.Event.class, View.Group.class,View.User.class})
    private String groupDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id")
    @JsonView(View.Event.class)
    private User moderator;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_event_id",referencedColumnName = "event_id")
    @JsonIgnore
    private Event lastEvent;

    @ManyToMany(mappedBy = "joinGroups",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonIgnore
    @JsonView(View.User.class)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
   // @JsonIgnore
    @JsonView( View.Group.class)
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

//    @Override
//    public String toString() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String str = null;
//        try {
//            str = objectMapper.writeValueAsString(this);
//        }
//        catch(JsonProcessingException jpe) {
//            jpe.printStackTrace();
//        }
//        return str;
//    }

    @Override
    public int hashCode() { return Objects.hash(groupId,groupName);}


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId ;
    }
}
