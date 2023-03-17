package br.com.agenda.agenda.estudante;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(path = "estudante")
public class EstudanteController {
    ArrayList<Estudante> users = new ArrayList<Estudante>(Arrays.asList(
            new Estudante(1L, "Lucas", "81 9 8441-7523", LocalDate.of(1999,11,07), "Desenvolvedor"),
            new Estudante(2L, "Claudio", "81 9 9999-9999", LocalDate.of(2007,12,11), "Padeiro"),
            new Estudante(3L, "Carlos", "81 9 9999-8888", LocalDate.of(2001,01,02), "Músico"),
            new Estudante(4L, "Pedro", "81 9 2222-1111", LocalDate.of(2000,07,22), "Pintor"),
            new Estudante(5L, "João", "81 9 1111-1111", LocalDate.of(2002,1,1), "Desenvolvedor")
    ));
    @GetMapping(value="{name}")
    public Estudante getByName(@PathVariable String name) {
        for (Estudante user: users) {
            if (user.getNome().equalsIgnoreCase(name)) return user;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Estudante> getAllUsers() {
        return users;
    }

    @GetMapping(value="/profissao/{profissao}")
    public List<Estudante> getByOccupation(@PathVariable String profissao) {
        ArrayList<Estudante> listOccupation = new ArrayList<Estudante>();
        for (Estudante user: users) {
            if (user.getProfissao().equalsIgnoreCase(profissao)) {
                listOccupation.add(user);
            };
        }

        if (listOccupation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return listOccupation;
    }
    @PostMapping
    public Estudante createUser(@RequestBody Estudante user) {
        users.add(user);

        return user;
    }

    @DeleteMapping(value="{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        int foundUserIndex = 0;
        for (Estudante user: users) {
            if (user.getId().equals(id)) {
                foundUserIndex = users.indexOf(user);
            };
        }

        users.remove(foundUserIndex);
    }

    @PutMapping(value="{id}")
    @ResponseStatus(HttpStatus.OK)
    public Estudante updateUser(@PathVariable Long id, @RequestBody Estudante updatedUser) {
        for (Estudante user: users) {
            if (user.getId().equals(id)) {
                user.setNome(updatedUser.getNome());
                user.setTelefone(updatedUser.getTelefone());
                user.setDtNascimento(updatedUser.getDtNascimento());
                user.setProfissao(updatedUser.getProfissao());
                updatedUser.setId(user.getId());
            };
        }

        return updatedUser;
    }

}
