package com.jax.fxbootdb.data.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@EntityScan
public class Users {
    private Integer id;

    private String username;

    private String password;

}
