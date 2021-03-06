package ood.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    @JsonView({View.Event.class, View.Group.class,View.Voting.class})
    private long eventId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voting_id",referencedColumnName = "voting_id")
    @JsonView(View.Voting.class)
    //@JsonIgnore
    private Voting voting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @JsonView({View.Event.class})
    //@JsonIgnore
    private Group group;

    //ttId1,ttId2
    @Column(name = "selected_movies")
    @JsonView({View.Event.class, View.Group.class,View.Voting.class})
    private String selectedMovies;

    @Column(name = "create_time")
    @JsonView({View.Event.class, View.Group.class,View.Voting.class})
    private OffsetDateTime createTime;

    @Column(name = "show_time")
    @JsonView({View.Event.class, View.Group.class,View.Voting.class})
    private OffsetDateTime showTime;

    @Column(name = "movie_decision")
    @JsonView({View.Event.class, View.Group.class,View.Voting.class})
    private String movieDecision;

    @OneToOne(mappedBy = "lastEvent", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Group lastEventGroup;


    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public Voting getVoting() {
        try{
            boolean flag = voting.equals(null);
        } catch (Exception e){
            return null;
        }
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    public Group getGroup() {
        try{
            boolean flag = group.equals(null);
        } catch (Exception e){
            return null;
        }
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getSelectedMovies() {
        return selectedMovies;
    }

    public void setSelectedMovies(String selectedMovies) {
        this.selectedMovies = selectedMovies;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }

    public OffsetDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(OffsetDateTime showTime) {
        this.showTime = showTime;
    }


    public String getMovieDecision(){
        return movieDecision;
    }

    public void setMovieDecision(String movieDecision) {
        this.movieDecision = movieDecision;
    }

    public Group getLastEventGroup() {
        try{
            boolean flag = group.equals(null);
        } catch (Exception e){
            return null;
        }
        return lastEventGroup;
    }

    public void setLastEventGroup(Group lastEventGroup) {
        this.lastEventGroup = lastEventGroup;
    }

}
