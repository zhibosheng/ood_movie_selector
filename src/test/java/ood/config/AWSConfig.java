package ood.config;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class AWSConfig {
    @Bean
    @Primary
    public AmazonS3 getAmazonS3mock(){
        return mock(AmazonS3.class);
    }
}
