package com.portfolio.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.portfolio.models.Hostname;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface HostnameRepository extends CrudRepository<Hostname, Long> {

    public Hostname getByName(String name);

    public Optional<Hostname> findByName(String name);
}
