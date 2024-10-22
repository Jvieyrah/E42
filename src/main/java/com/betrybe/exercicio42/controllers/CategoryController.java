package com.betrybe.exercicio42.controllers;

import com.betrybe.exercicio42.controllers.dto.CategoryDTO;
import com.betrybe.exercicio42.controllers.dto.ResponseDTO;
import com.betrybe.exercicio42.models.entities.Category;
import com.betrybe.exercicio42.services.CategoryService;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  ResponseEntity<ResponseDTO<Category>> createCategory(@RequestBody CategoryDTO categoryDTO) {
    Category category = categoryService.insertCategory(categoryDTO.toCategory());
    ResponseDTO<Category> newCategory = new ResponseDTO<>("Categoria criada com sucesso!", category);
    return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
  }

  @PutMapping("/{id}")
  ResponseEntity<ResponseDTO<Category>> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
    Optional<Category> category = categoryService.updateCategory(id, categoryDTO.toCategory());
    if (category.isEmpty()) {
      ResponseDTO<Category> notFoundCategory = new ResponseDTO<>("Categoria não encontrada!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundCategory);
    }
    ResponseDTO<Category> updatedCategory = new ResponseDTO<>("Categoria alterada com sucesso!!", category.get());
    return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ResponseDTO<Category>> removeCategory(@PathVariable Long id) {
    Optional<Category> category = categoryService.removeCategory(id);
    if (category.isEmpty()) {
      ResponseDTO<Category> notFoundCategory = new ResponseDTO<>("Categoria não encontrada!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundCategory);
    }
    ResponseDTO<Category> removedCategory = new ResponseDTO<>("Categoria removida com sucesso!!", category.get());
    return ResponseEntity.status(HttpStatus.OK).body(removedCategory);
  }

  @GetMapping("/{id}")
  ResponseEntity<ResponseDTO<Category>> getCategoryById(@PathVariable Long id) {
    Optional<Category> category = categoryService.getCategoryByID(id);
    if (category.isEmpty()) {
      ResponseDTO<Category> notFoundCategory = new ResponseDTO<>("Categoria não encontrada!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundCategory);
    }
    ResponseDTO<Category> foundCategory = new ResponseDTO<>("Categoria encontrada com sucesso!!", category.get());
    return ResponseEntity.status(HttpStatus.OK).body(foundCategory);
  }

  @GetMapping
  List<CategoryDTO> getAllCategories() {
    List<Category> allCategories = categoryService.getAllCategories();
    return allCategories.stream().map((category) -> new CategoryDTO(
            category.getId(), category.getName()))
        .collect(Collectors.toList());
  }
}
