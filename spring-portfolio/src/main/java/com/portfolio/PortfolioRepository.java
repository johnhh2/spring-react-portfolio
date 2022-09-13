package com.portfolio;

import org.springframework.data.repository.CrudRepository;

import com.portfolio.Portfolio;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PortfolioRepository extends CrudRepository<Portfolio, String> {

}
