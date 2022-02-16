package com.atomtech.ecommerce.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "prod_id",
        scope     = long.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long prod_id;
    private double prix;
    private String nom;
    private String descript;
    private String model;
    private int quantity;
    private String couleur;
    private boolean sexe;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore   // ignorer ce champ
    private List<OrderDetails> orderDetails;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference   // defini comme Parent : pour gerer la boucle infinie au niveau du Json
    private List<Image> images = new ArrayList<>();

}
