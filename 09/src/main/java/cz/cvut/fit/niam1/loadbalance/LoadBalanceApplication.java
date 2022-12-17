package cz.cvut.fit.niam1.loadbalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@EnableAutoConfiguration
public class LoadBalanceApplication {
    List<String> healthy_URLs;
    List<String> unhealthy_URLs;
    private final Logger logger = LoggerFactory.getLogger(LoadBalanceApplication.class);

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public LoadBalanceApplication() {
       this.healthy_URLs = new ArrayList<String>() {
           {
               add("http://147.32.233.18:8888/MI-MDW-LastMinute1/list");
               add("http://147.32.233.18:8888/MI-MDW-LastMinute2/list");
               add("http://147.32.233.18:8888/MI-MDW-LastMinute3/list");
           }
       };
       this.unhealthy_URLs = new ArrayList<>();

       Timer timer = new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               checkHealthyURLs();
               checkUnhealthyURLs();
           }
       }, 3000, 1000);
   }

   private void checkHealthyURLs() {
        for (int i = 0; i < this.healthy_URLs.size(); i++){
            String url = this.healthy_URLs.get(i);
            int status_code = monitoringRequest(url);
            if (status_code != 200) {
                this.unhealthy_URLs.add(url);
                this.healthy_URLs.remove(i);
                logger.info("{}: {} UNHEALTHY",status_code, url);
            } else {
                logger.info("{}: {} HEALTHY", status_code, url);
            }
        }
   }

   private void checkUnhealthyURLs() {
       for (int i = 0; i < this.unhealthy_URLs.size(); i++){
           String url = this.unhealthy_URLs.get(i);
           int status_code = monitoringRequest(url);
           if (status_code == 200) {
               this.healthy_URLs.add(url);
               this.unhealthy_URLs.remove(i);
               logger.info("{}: {} HEALTHY", status_code, url);
           } else {
               logger.info("{}: {} UNHEALTHY", status_code, url);
           }
       }
   }

   private int monitoringRequest(String url) {
       try {
           ResponseEntity<String> rE = this.restTemplate.exchange(new URI(url), HttpMethod.GET, null, String.class);
           return 200;
       } catch (HttpServerErrorException.InternalServerError | URISyntaxException e) {
           return 500;
       }
   }

    @GetMapping(value = "/test")
    public ResponseEntity<String> test(HttpServletRequest request) throws Exception {
        logger.info("----------------------------------------------------------");
        // copy headers
        HttpHeaders headers = new HttpHeaders();
        Collections.list(request.getHeaderNames()).forEach(head -> headers.add(head, request.getHeader(head)));
        // create request entity
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        // HTTP
        ResponseEntity<String> responseEntity;

        while (true){
            int selection_index = new Random().nextInt(healthy_URLs.size());
            String picked_url = healthy_URLs.get(selection_index);
            int status_code;
            try {
                responseEntity = restTemplate.exchange(new URI(picked_url), HttpMethod.GET, requestEntity, String.class);
                status_code = responseEntity.getStatusCode().value();
                // OK
                if (status_code == 200) {
                    logger.info("{}: {} USED", status_code, picked_url);
                    logger.info("----------------------------------------------------------");
                    return responseEntity;
                }
            } catch (Exception e) {
                this.unhealthy_URLs.add(picked_url);
                this.healthy_URLs.remove(selection_index);
                logger.info("500: {} UNHEALTHY", picked_url);
            }
        }
    }

   public static void main(String[] args) {
       SpringApplication.run(LoadBalanceApplication.class, args);
   }

}