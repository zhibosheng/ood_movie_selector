package ood.model;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "votings")
public class Voting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long votingId;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @Column(name = "voting_result")
    private String votingResult;

    public long getVotingId() {
        return votingId;
    }

    public void setVotingId(long votingId) {
        this.votingId = votingId;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    public String getVotingResult() {
        return votingResult;
    }

    public void setVotingResult(String votingResult) {
        this.votingResult = votingResult;
    }
}
