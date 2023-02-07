package com.restapi.adminBackend.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restapi.adminBackend.dao.CategoryDao;
import com.restapi.adminBackend.entities.Category;
import com.restapi.adminBackend.services.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService{

	@Autowired
	public CategoryDao categoryDao;

	@Override
	public List<Category> categories() {
		return categoryDao.findAll();
	}

	@Override
	public Category fetchCategory(long id) {
		Category exisCategory = categoryDao.findById(id).orElseThrow(null);
		return exisCategory;
	}

	@Override
	public void addCategory(Category category) {
		categoryDao.save(category);		
	}

	@Override
	public void editCategory(Category category, long id) {
		Category findCategory = categoryDao.findById(id).orElseThrow(null);
		System.out.println(category);
		findCategory.setName(category.getName());
		findCategory.setCode(category.getCode());		
		categoryDao.save(findCategory);		
	}

	@Override
	public void deleteCategory(long id) {
		categoryDao.deleteById(id);		
	}

	@Override
	public void changeStatus(long id) {
		Category findCategory = categoryDao.findById(id).orElseThrow(null);
		System.out.println(findCategory);
		System.out.println(findCategory.getStatus());
		if(findCategory.getStatus() == 0) {
			findCategory.setStatus(1);
		}else {
			findCategory.setStatus(0);
		}
		categoryDao.save(findCategory);
	}


	
	
}
