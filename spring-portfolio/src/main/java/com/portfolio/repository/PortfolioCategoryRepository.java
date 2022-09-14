package com.portfolio.repository;

import org.springframework.data.repository.CrudRepository;

import com.portfolio.models.PortfolioCategory;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PortfolioCategoryRepository 
    extends CrudRepository<PortfolioCategory, Long> {

}
