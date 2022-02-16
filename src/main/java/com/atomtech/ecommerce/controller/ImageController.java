package com.atomtech.ecommerce.controller;
import com.atomtech.ecommerce.model.Image;
import com.atomtech.ecommerce.model.Product;
import com.atomtech.ecommerce.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ImageController implements ServletContextAware {

    private final String IMG_PATH = "/uploads/images/";

    //    la portabilite des chemins
    private String sep = File.separator;
    private final String IMAGES_DIRECTORY = sep+"uploads"+sep+"images"+sep;

    @Autowired
    private ImageService imageService;

    private ServletContext servletContext;

//    @PostMapping(value = "/images")
//    public ResponseEntity<Object> add(@RequestBody Image image) {
//        return imageService.add(image);
//    }

    @PostMapping(value = "/upload/{prod_id}")
    public ResponseEntity<Object> upload(@PathVariable long prod_id, @RequestParam("files") MultipartFile[] files)
    {
        try {
            System.out.println("File list:");
            Image image;
            Product product = new Product();
            product.setProd_id(prod_id);
            for (MultipartFile file: files)
            {
                System.out.println("File name: "+file.getOriginalFilename());
                System.out.println("File size: "+file.getSize());
                System.out.println("File type: "+file.getContentType());
                System.out.println("-----------------------------------------");
                image = new Image();
                image.setProduct(product);
                image.setUrl(IMG_PATH+save(file));
                imageService.add(image);
            }
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println("Error lors de l'opload");
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    private String save(MultipartFile file)
    {
        System.out.println("Chemin: "+IMAGES_DIRECTORY+"fileName");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyymmss");
            String newFileName = simpleDateFormat.format(new Date()) + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(this.servletContext.getRealPath(IMAGES_DIRECTORY+newFileName));
            Files.write(path, bytes);
            return newFileName;
        }
        catch (Exception e)
        {
            System.out.println("Error in save");
            return null;
        }
    }

    @GetMapping(value = "/images")
    public List<Image> getAll() {
        return imageService.getAll();
    }

    @GetMapping(value = "/images/{img_id}")
    public ResponseEntity<Object> getOne(@PathVariable long img_id) {
        return imageService.getOne(img_id);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
