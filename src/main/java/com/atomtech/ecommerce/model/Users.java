package com.atomtech.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "user_id",
        scope     = long.class)
public class Users extends RepresentationModel<Users> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    private String first_name;
    private String last_name;
    private String address;
    private String phone_number;
    private String login;
    private String pwd;
    private int role;
    private boolean isActived;
    private Date createAt;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonManagedReference // parent de Order
    private List<Orders> orders = new ArrayList<>();

}
