package com.atomtech.ecommerce.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "orderId",
        scope     = long.class)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private Date orderDate;
    private int etat;

    @ManyToOne
    @JsonBackReference   // objet enfant
    private Users users;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails = new ArrayList<>();
}
