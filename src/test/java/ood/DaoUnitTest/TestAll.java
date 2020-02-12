package ood.DaoUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserDaoTest.class,
        GroupDaoTest.class,
        EventDaoTest.class,
        VotingDaoTest.class

})
public class TestAll {

}

