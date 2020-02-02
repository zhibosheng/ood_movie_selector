package ood.model;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long eventId;

    @OneToOne
    @JoinColumn(name = "voting_id",referencedColumnName = "voting_id")
    private Voting voting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "create_time")
    private OffsetDateTime createTime;

    @Column(name = "show_time")
    private OffsetDateTime showTime;

    //mingqian modify 2020-2-1
    @Column(name = "movie_decision")
    private String movieDecision;

    @OneToOne(mappedBy = "lastEvent", cascade = CascadeType.REMOVE)
    private Group lastEventGroup;


    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    //mingqian modify 2020-2-1
    public String getMovieDecision(){
        return movieDecision;
    }
    //mingqian modify 2020-2-1
    public void setMovieDecision(String movieDecision) {
        this.movieDecision = movieDecision;
    }

    public Group getLastEventGroup() {
        return lastEventGroup;
    }

    public void setLastEventGroup(Group lastEventGroup) {
        this.lastEventGroup = lastEventGroup;
    }

}
