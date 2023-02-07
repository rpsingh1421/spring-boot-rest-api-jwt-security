package com.restapi.adminBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.adminBackend.entities.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
