package ood.service;

import ood.ApplicationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public class MessageServiceTest {
    @Autowired
    MessageService messageService;

    @Test
    public void sendEmail(){
        String TO = "zhibosheng@gwu.edu";
        String SUBJECT = "Group invitation from MovieSelector Website";
        String HTMLBODY = "<h1>Hi,</h1>" +
                "<p>Your friend is inviting you to join his/her movie watching group. " +
                "Please click the following link: <a href='https://aws.amazon.com/ses/'>123456789</a>. " +
                "If you are a new user, " +
                "you are welcome to click to register a new account for free. " +
                "If you already have an account, please log in to join this new group.</p>";
        String TEXTBODY = "Message from movieSelector website. This is a website for friends and family" +
                "to choose movies to watch. Here you can join different groups, vote on movie" +
                "watching activities and browse related information.";
        try{
            messageService.sendEmail(TO,SUBJECT,HTMLBODY,TEXTBODY);
        } catch (Exception ex){
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }

    }
}
