package com.example.homebanking.repositories;

import com.example.homebanking.models.ClientLoans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoansRepository extends JpaRepository<ClientLoans,Long> {


}
