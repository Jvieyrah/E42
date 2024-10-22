package com.betrybe.exercicio42.controllers.dto;

import com.betrybe.exercicio42.models.entities.Category;

public record CategoryDTO(Long id, String name) {

  public Category toCategory(){
    return new Category(id, name);
  }

}
