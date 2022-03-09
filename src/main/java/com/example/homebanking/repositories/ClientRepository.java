package com.example.homebanking.repositories;


import com.example.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Arrays;
import java.util.List;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long>{

Client findByEmail (String email);

}
