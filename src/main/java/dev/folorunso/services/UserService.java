package dev.folorunso.services;

import dev.folorunso.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    RestTemplate restTemplate = new RestTemplate();
    @Value("${server.url}")
    String serverURL;


    public List<User> getAllUsers() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> restTemplate.getForObject(serverURL, List.class),
                throwable -> new ArrayList());
    }

    public User getUserById(int id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> restTemplate.getForObject(serverURL + "/" + id, User.class),
                throwable -> null);
    }

    public void addUser(User user) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        circuitBreaker.run(() -> restTemplate.postForEntity(serverURL, user, User.class),
                throwable -> null);
    }

    public void updateUser(int id, User user) {
        restTemplate.put(serverURL + "/" + id, user, User.class);
    }

    public void deleteUserById(int id) {
        restTemplate.delete(serverURL + "/" + id);
    }
}
