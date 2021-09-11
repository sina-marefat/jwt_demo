package com.jwt.demo.jwt_demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

}
