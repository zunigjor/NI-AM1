package cz.fit.cvut.niam1.transofmation;

import cz.cvut.fit.niam1.transformation.TransformationWebApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TransformationWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransformationWebApplicationTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void testExample1() throws Exception {
        String name = "transformation";
        String input = new String(Files.readAllBytes(Paths.get("example/input1.txt")), StandardCharsets.UTF_8);
        String output = new String(Files.readAllBytes(Paths.get("example/output1.txt")), StandardCharsets.UTF_8);

        ResponseEntity<String> response = template.postForEntity(base.toString() + name, input, String.class);
        System.out.println(response.getBody());
        assertThat(response.getBody()).isEqualTo(output);
    }

    @Test
    public void testExample2() throws Exception {
        String name = "transformation";
        String input = new String(Files.readAllBytes(Paths.get("example/input2.txt")), StandardCharsets.UTF_8);
        String output = new String(Files.readAllBytes(Paths.get("example/output2.txt")), StandardCharsets.UTF_8);

        ResponseEntity<String> response = template.postForEntity(base.toString() + name, input, String.class);
        System.out.println(response.getBody());
        assertThat(response.getBody()).isEqualTo(output);
    }

    @Test
    public void testExample3() throws Exception {
        String name = "transformation";
        String input = new String(Files.readAllBytes(Paths.get("example/input3.txt")), StandardCharsets.UTF_8);
        String output = new String(Files.readAllBytes(Paths.get("example/output3.txt")), StandardCharsets.UTF_8);

        ResponseEntity<String> response = template.postForEntity(base.toString() + name, input, String.class);
        System.out.println(response.getBody());
        assertThat(response.getBody()).isEqualTo(output);
    }

    @Test
    public void testExample4() throws Exception {
        String name = "transformation";
        String input = new String(Files.readAllBytes(Paths.get("example/input4.txt")), StandardCharsets.UTF_8);
        String output = new String(Files.readAllBytes(Paths.get("example/output4.txt")), StandardCharsets.UTF_8);

        ResponseEntity<String> response = template.postForEntity(base.toString() + name, input, String.class);
        System.out.println(response.getBody());
        assertThat(response.getBody()).isEqualTo(output);
    }

    @Test
    public void testExample5() throws Exception {
        String name = "transformation";
        String input = new String(Files.readAllBytes(Paths.get("example/input5.txt")), StandardCharsets.UTF_8);
        String output = new String(Files.readAllBytes(Paths.get("example/output5.txt")), StandardCharsets.UTF_8);

        ResponseEntity<String> response = template.postForEntity(base.toString() + name, input, String.class);
        System.out.println(response.getBody());
        assertThat(response.getBody()).isEqualTo(output);
    }
}
