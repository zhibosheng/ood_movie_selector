package ood.model;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;

    @Column(name = "voting_id")
    private long votingId;

    @Column(name = "group_id")
    private long groupId;

    @Column(name = "create_time")
    private OffsetDateTime createTime;

    @Column(name = "show_time")
    private OffsetDateTime showTime;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getVotingId() {
        return votingId;
    }

    public void setVotingId(long votingId) {
        this.votingId = votingId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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
}
