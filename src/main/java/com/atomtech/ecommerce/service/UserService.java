package com.atomtech.ecommerce.service;

import com.atomtech.ecommerce.model.Users;
import com.atomtech.ecommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<Object> add(@RequestBody Users users) {
        try
        {
            users.setPwd(this.passwordEncoder.encode(users.getPwd()));
            users.setCreateAt(new Date());
            Users users1 = usersRepository.save(users);

            if(users1 == null)
                return ResponseEntity.noContent().build();

            return ResponseEntity.status(201).body(users1);
        }
        catch (Exception e)
        {
            System.out.println("AAS::::) ERROR: "+e.getMessage());
            return ResponseEntity.noContent().build();
        }

    }

    public ResponseEntity<Object> login(@RequestBody Users users) {

        try
        {
            Users users1 = usersRepository.findByLogin(users.getLogin());

            if (users1 == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette utilisateur n'existe pas !'}");

            if (!BCrypt.checkpw(users.getPwd(), users1.getPwd()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Mot de passe incorrect !'}");

            URI location = ServletUriComponentsBuilder
                    .fromCurrentServletMapping()
                    .path("/rvs/{id}/users")
                    .buildAndExpand(users1.getUser_id())
                    .toUri();

//        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RvsController.class).getOneUserRvs(users1.getUser_id())).withRel("rvsList");
//        users1.add(link);

            return ResponseEntity.ok(users1);
        }
        catch (Exception e)
        {
            System.out.println("AAS::::) ERROR: "+e.getMessage());
            return ResponseEntity.noContent().build();
        }

    }

    public ResponseEntity<Object> update(@PathVariable long user_id, @RequestBody Users users) {

        try {
            Users users1 = usersRepository.findById(user_id);
            if(users1 == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cet utilisateur n'existe pas !'}");

            users.setUser_id(users1.getUser_id());
            users.setPwd(this.passwordEncoder.encode(users.getPwd()));
            users1 = usersRepository.save(users);
            if(users1 == null)
                return ResponseEntity.noContent().build();

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(users1.getUser_id())
                    .toUri();

            return ResponseEntity.ok(location);
        }
        catch (Exception e)
        {
            System.out.println("AAS::::) ERROR: "+e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public ResponseEntity<Object> getOne(@PathVariable long user_id) {
        Users users = usersRepository.findById(user_id);
        if (users == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette utilisateur n'existe pas !'}");


        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/rvs/{id}/users")
                .buildAndExpand(users.getUser_id())
                .toUri();

//        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RvsController.class).getOneUserRvs(user_id)).withRel("rvList");
//        users.add(link);

        return ResponseEntity.created(location).body(users);
    }
}
