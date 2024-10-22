package com.betrybe.exercicio42.controllers.dto;

import com.betrybe.exercicio42.models.entities.Brand;

public record BrandDTO (Long id, String name){
  public Brand toBrand(){
    return new Brand(id,name);
  }

}
