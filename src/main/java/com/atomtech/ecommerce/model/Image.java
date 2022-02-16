package com.atomtech.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "img_id",
        scope     = long.class)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long img_id;
    private String url;

    @ManyToOne
    @JsonBackReference
    private Product product;

}
