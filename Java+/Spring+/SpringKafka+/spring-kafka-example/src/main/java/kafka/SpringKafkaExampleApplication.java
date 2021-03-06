package kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaExampleApplication {

    public static void main(String[] args) {
        System.setProperty("kafka.bootstrapAddress", "localhost:32769");
        System.setProperty("topic", "my_topic");
        System.setProperty("group", "my_group");
        SpringApplication.run(SpringKafkaExampleApplication.class, args);
    }

}
