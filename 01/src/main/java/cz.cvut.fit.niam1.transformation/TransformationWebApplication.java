package cz.cvut.fit.niam1.transformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TransformationWebApplication {

    @PostMapping(value = "/transformation", consumes= "text/plain")
    String transformation(@RequestBody String message) {
        return new TransformationService(message).process();
    }

    public static void main(String[] args){
        SpringApplication.run(TransformationWebApplication.class, args);
    }

}
