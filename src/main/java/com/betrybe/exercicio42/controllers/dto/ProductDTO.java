package com.betrybe.exercicio42.controllers.dto;

import com.betrybe.exercicio42.models.entities.Product;

public record ProductDTO(Long id, String  name, String price) {

  public Product toProduct(){
    return new Product(id, name, price);
  }

}
