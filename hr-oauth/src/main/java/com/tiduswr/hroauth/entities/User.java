package com.tiduswr.hroauth.entities;

import java.util.Set;

public record User(
    Long id,
    String name,
    String email,
    String password,
    Set<Role> roles
){}
