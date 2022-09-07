package dev.folorunso.services;

import dev.folorunso.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    RestTemplate restTemplate = new RestTemplate();
    @Value("${server.url}")
    String serverURL;


    public List getAllUsers() {
        return restTemplate.getForObject(serverURL, List.class);
    }

    public User getUserById(int id) {
        return restTemplate.getForObject(serverURL + "/" + id, User.class);
    }

    public void addUser(User user) {
        restTemplate.postForEntity(serverURL, user, User.class);
    }

    public void updateUser(int id, User user) {
        restTemplate.put(serverURL + "/" + id, user, User.class);
    }

    public void deleteUserById(int id) {
        restTemplate.delete(serverURL + "/" + id);
    }
}
