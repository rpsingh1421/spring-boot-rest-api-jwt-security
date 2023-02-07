package com.restapi.adminBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.adminBackend.Response.Response;
import com.restapi.adminBackend.entities.Category;
import com.restapi.adminBackend.services.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/get_categories")
	public List<Category> getList() {
		return categoryService.categories();
	}
	@GetMapping("/get_category/{id}")
	public Category category(@PathVariable ("id") long id) {
		
		return categoryService.fetchCategory(id);
	}
	@GetMapping("/change_status/{id}")
	public Response changeStatus(@PathVariable ("id") long id) {
		try {
			categoryService.changeStatus(id);
			return new Response(true,"Status Changed Successfully");
			} catch (Exception e) {
				return new Response(false,"OOops...Status Not Changed");
		}
	}
	
	@PostMapping("/addCategory")
	public Response addCategory(@RequestBody Category category) {
		try {
			categoryService.addCategory(category);
			return new Response(true,"Category Added Successfully");
		} catch (Exception e) {
			return new Response(false,"Failed ...Category Not Added");
			
		}	
	}
	@PutMapping("/update_category/{id}")
	public Response editCategory(@RequestBody Category category, @PathVariable ("id") long id) {
		try {
			categoryService.editCategory(category,id);
			return new Response(true,"Category Updated Successfully");
		} catch (Exception e) {
			return new Response(false,"Category Updation Failed");
		}
	}
	@DeleteMapping("/deleteCategory/{id}")
	public Response deleteCategory(@PathVariable ("id") long id) {
		try {
			categoryService.deleteCategory(id);
			return new Response(true,"Category Deleted Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			return new Response(false,"Category Not deleted...INTERNAL Server Error ");
		}
	}
}
