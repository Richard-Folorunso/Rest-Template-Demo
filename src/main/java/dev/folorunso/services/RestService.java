package dev.folorunso.services;

import dev.folorunso.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestService {

    RestTemplate restTemplate = new RestTemplate();
    @Value("${base.url}")
    String baseURL;


    public List getAllUsers() {
        return restTemplate.getForObject(baseURL, List.class);
    }

    public User getUserById(int id) {
        return restTemplate.getForObject(baseURL + "/" + id, User.class);
    }

    public void addUser(User user) {
        restTemplate.postForEntity(baseURL, user, User.class);
    }

    public void updateUser(int id, User user) {
        restTemplate.put(baseURL + "/" + id, user, User.class);
    }

    public void deleteUserById(int id) {
        restTemplate.delete(baseURL + "/" + id);
    }
}
