package cue.edu.co.s3.config;

import cue.edu.co.s3.config.model.S3ConnectionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.metrics.MetricPublisher;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client(S3ConnectionProperties s3Properties, MetricPublisher publisher) {
        return S3Client.builder()
                .overrideConfiguration(o -> o.addMetricPublisher(publisher))
                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .region(Region.of(s3Properties.region()))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner(S3ConnectionProperties props) {
        return S3Presigner.builder()
                .region(Region.of(props.region()))
                .build();
    }

}
