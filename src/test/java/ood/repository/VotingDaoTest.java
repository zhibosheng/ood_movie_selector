package ood.repository;

import ood.ApplicationBoot;
import ood.model.Voting;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class VotingDaoTest {
    @Autowired
    private VotingDaoImpl votingDao;
    private Voting votingRecord1 = new Voting();
    private Voting votingRecord2 = new Voting();

    @Before
    public void setup() {

       votingRecord1.setStartTime(OffsetDateTime.parse("2020-05-20T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
       votingRecord1.setEndTime(OffsetDateTime.parse("2020-05-21T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));

       votingRecord2.setStartTime(OffsetDateTime.parse("2020-03-20T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
       votingRecord2.setEndTime(OffsetDateTime.parse("2020-03-21T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
       votingDao.save(votingRecord2);
    }
    @Test
    public void save(){
        votingDao.save(votingRecord1);
        Assert.assertNotNull(votingRecord1.getVotingId());
    }

    @Test
    public void update(){
        votingRecord1.setStartTime(OffsetDateTime.parse("2020-05-20T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        votingDao.update(votingRecord1);
        Assert.assertEquals(votingRecord1.getStartTime(),OffsetDateTime.parse("2020-05-20T20:30:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

    @Test
    public void delete(){
        votingDao.delete(votingRecord2);
    }

    @After
    public void cleanUp(){
        votingDao.delete(votingRecord1);
        if(votingRecord2 != null) votingDao.delete(votingRecord2);
    }
}
