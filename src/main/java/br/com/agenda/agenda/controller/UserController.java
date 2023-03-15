package br.com.agenda.agenda.controller;

import br.com.agenda.agenda.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class UserController {
    ArrayList<User> users = new ArrayList<User>(Arrays.asList(
            new User(1L, "Lucas", "81 9 8441-7523", LocalDate.of(1999,11,07), "Desenvolvedor"),
            new User(2L, "Claudio", "81 9 9999-9999", LocalDate.of(2007,12,11), "Padeiro"),
            new User(3L, "Carlos", "81 9 9999-8888", LocalDate.of(2001,01,02), "Músico"),
            new User(4L, "Pedro", "81 9 2222-1111", LocalDate.of(2000,07,22), "Pintor"),
            new User(5L, "João", "81 9 1111-1111", LocalDate.of(2002,1,1), "Desenvolvedor")
    ));
    @GetMapping(value="/user/{name}")
    public User getByName(@PathVariable String name) {
        for (User user: users) {
            if (user.getName().equalsIgnoreCase(name)) return user;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/user")
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping(value="/user/occupation/{occupation}")
    public List<User> getByOccupation(@PathVariable String occupation) {
        ArrayList<User> listOccupation = new ArrayList<User>();
        for (User user: users) {
            if (user.getOccupation().equalsIgnoreCase(occupation)) {
                listOccupation.add(user);
            };
        }

        if (listOccupation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return listOccupation;
    }

    @PostMapping(value="/user")
    public User createUser(@RequestBody User user) {
        users.add(user);

        return user;
    }

    @DeleteMapping(value="/user/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        int foundUserIndex = 0;
        for (User user: users) {
            if (user.getId().equals(id)) {
                foundUserIndex = users.indexOf(user);
            };
        }

        users.remove(foundUserIndex);
    }

    @PutMapping(value="/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user: users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setPhone(updatedUser.getPhone());
                user.setBirthDate(updatedUser.getBirthDate());
                user.setOccupation(updatedUser.getOccupation());
                updatedUser.setId(user.getId());
            };
        }

        return updatedUser;
    }

}
