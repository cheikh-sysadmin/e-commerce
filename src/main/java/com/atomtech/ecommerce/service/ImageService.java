package com.atomtech.ecommerce.service;

import com.atomtech.ecommerce.model.Image;
import com.atomtech.ecommerce.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Transactional
public class ImageService {

    @Autowired private ImageRepository imageRepository;

    public ResponseEntity<Object> add(@RequestBody Image image) {
        try
        {
            Image image1 = imageRepository.save(image);

            if(image1 == null)
                return ResponseEntity.noContent().build();

            return ResponseEntity.status(201).body(image1);
        }
        catch (Exception e)
        {
            System.out.println("AAS::::) ERROR: "+e.getMessage());
            return ResponseEntity.noContent().build();
        }


    }


    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    public ResponseEntity<Object> getOne(@PathVariable long img_id) {
        Image image = imageRepository.findById(img_id);
        if (image == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette image n'existe pas !'}");


        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/rvs/{id}/users")
                .buildAndExpand(image.getImg_id())
                .toUri();

//        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RvsController.class).getOneUserRvs(user_id)).withRel("rvList");
//        users.add(link);

        return ResponseEntity.created(location).body(image);
    }
}
