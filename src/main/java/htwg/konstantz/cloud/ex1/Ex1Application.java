package htwg.konstantz.cloud.ex1;

import htwg.konstantz.cloud.ex1.configuration.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Ex1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex1Application.class, args);
    }
}
