package org.but.feec.carservice.service;

import org.but.feec.carservice.data.PersonRepository;

public class AuthService {

    private PersonRepository personRepository;

    public AuthService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
