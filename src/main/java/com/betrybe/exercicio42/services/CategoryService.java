package com.betrybe.exercicio42.services;

import com.betrybe.exercicio42.models.entities.Category;
import com.betrybe.exercicio42.models.repositories.CategoryRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category insertCategory (Category category ) {
    return categoryRepository.save(category);
  }
  public Optional<Category> updateCategory (Long id, Category category ) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (optionalCategory.isPresent()){
      Category categoryFromDb = optionalCategory.get();
     categoryFromDb.setName(category.getName());
      Category updatedProduct = categoryRepository.save(categoryFromDb);
      return Optional.of(updatedProduct);
    }
    return optionalCategory;
  }

  public Optional<Category> removeCategory  (Long id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (optionalCategory.isPresent()){
      categoryRepository.deleteAllById(Collections.singleton(id));
    }
    return optionalCategory;
  }

  public Optional<Category> getCategoryByID (Long id) {
    return categoryRepository.findById(id);
  }

  public List<Category> getAllCategories () {
    return categoryRepository.findAll();
  }



}
