package com.portfolio.repository;

import org.springframework.data.repository.CrudRepository;

import com.portfolio.models.Account;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AccountRepository extends CrudRepository<Account, Long> {

}
