package com.atomtech.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "od_id",
        scope     = long.class)
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long od_id;
    private int quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders order;



}
