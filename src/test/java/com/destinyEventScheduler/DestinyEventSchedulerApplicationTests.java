package com.destinyEventScheduler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"bungie.key=0", "jwt.secret=12341234", "encrypt_key=0"})
public class DestinyEventSchedulerApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Before
	public void init(){

	}
	
    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/api/", String.class);
    }
}
