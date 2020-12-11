package suranovan.springboot;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    public static GenericContainer<?> devApp = new GenericContainer<>("devapp").withExposedPorts(8080);
    public static GenericContainer<?> prodApp = new GenericContainer<>("prodapp").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @AfterEach
    void status() {
        System.out.println("Test pass!");
    }

    @Test
    void checkResponseForDev() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" +
                devApp.getMappedPort(8080) + "/profile", String.class);
        Assert.assertEquals(forEntity.getBody(), "Current profile is Dev");
        System.out.println("body is correct");
        Assert.assertEquals(forEntity.getStatusCodeValue(), 200);
        System.out.println("status code is correct");
    }

    @Test
    void checkResponseForProd() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" +
                prodApp.getMappedPort(8081) + "/profile", String.class);
        Assert.assertEquals(forEntity.getBody(), "Current profile is production");
        System.out.println("body is correct");
        Assert.assertEquals(forEntity.getStatusCodeValue(), 200);
        System.out.println("status code is correct");
    }
}
