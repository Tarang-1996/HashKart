package com.hashkart.inventory.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashkart.inventory.service.model.Category;
import com.hashkart.inventory.service.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;

	public void createCategory(Category category) {
		categoryRepo.save(category);
	}

	public List<Category> listCategory() {
		return categoryRepo.findAll();
	}

	public void editCategory(int categoryId, Category updateCategory) {
		Category category = categoryRepo.getById(categoryId);
		category.setCategoryName(updateCategory.getCategoryName());
		category.setDescription(updateCategory.getDescription());
		categoryRepo.save(category);
	}

	public boolean findById(int categoryId) {
		return categoryRepo.findById(categoryId).isPresent();
	}
}
