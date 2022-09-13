package com.portfolio;

import org.springframework.data.repository.CrudRepository;

import com.portfolio.Hostname;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface HostnameRepository extends CrudRepository<Hostname, Integer> {

    public Hostname findByName(String name);
}
