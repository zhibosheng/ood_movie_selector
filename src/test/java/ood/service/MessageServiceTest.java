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
        String SUBJECT = "Amazon SES test (AWS SDK for Java)";
        String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
                + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
                + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>"
                + "AWS SDK for Java</a>";
        String TEXTBODY = "This email was sent through Amazon SES "
                + "using the AWS SDK for Java.";
        try{
            messageService.sendEmail(TO,SUBJECT,HTMLBODY,TEXTBODY);
        } catch (Exception ex){
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }

    }
}
