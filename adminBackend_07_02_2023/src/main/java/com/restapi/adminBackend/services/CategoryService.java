package com.restapi.adminBackend.services;

import java.util.List;

import com.restapi.adminBackend.entities.Category;

public interface CategoryService {


	public List<Category> categories();

	public Category fetchCategory(long id);

	public void addCategory(Category category);

	public void editCategory(Category category, long id);

	public void deleteCategory(long id);

	public void changeStatus(long id);
}
