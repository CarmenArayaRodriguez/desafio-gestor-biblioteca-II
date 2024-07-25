package cl.praxis.GestorBiblioteca.service;

import cl.praxis.GestorBiblioteca.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private Map<String, User> users = new HashMap<>();

    public UserService() {
        users.put("1", new User("1", "Cristina Zaror", "cristina@gmail.com"));
        users.put("2", new User("2", "Mario Salinas", "mario@gmail.com"));
        users.put("1", new User("1", "Sara Cabrera", "sara@gmail.com"));
        users.put("2", new User("2", "Carlos Pizarro", "carlos@gmail.com"));
        users.put("1", new User("1", "Osvaldo Vega", "osvaldo@gmail.com"));
        users.put("2", new User("2", "Pamela Arancibia", "pamela@gmail.com"));
    }

    public User getUserById(String id) {
        return users.get(id);
    }

    public void addUser(String id, String name, String email) {
        users.put(id, new User(id, name, email));
    }
}
