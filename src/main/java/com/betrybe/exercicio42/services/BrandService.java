package com.betrybe.exercicio42.services;

import com.betrybe.exercicio42.models.entities.Brand;
import com.betrybe.exercicio42.models.entities.Category;
import com.betrybe.exercicio42.models.repositories.BrandRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
  private final BrandRepository brandRepository;

  @Autowired
  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public Brand insertBrand (Brand brand ) {
    return brandRepository.save(brand);
  }
  public Optional<Brand> updateBrand (Long id, Brand brand ) {
    Optional<Brand> optionalBrand = brandRepository.findById(id);
    if (optionalBrand.isPresent()){
      Brand brandFromDb = optionalBrand.get();
      brandFromDb.setName(brand.getName());
      Brand updatedBrand = brandRepository.save(brandFromDb);
      return Optional.of(updatedBrand);
    }
    return optionalBrand;
  }

  public Optional<Brand> removeBrand  (Long id) {
    Optional<Brand> optionalBrand = brandRepository.findById(id);
    if (optionalBrand.isPresent()){
      brandRepository.deleteAllById(Collections.singleton(id));
    }
    return optionalBrand;
  }

  public Optional<Brand> getBrandByID (Long id) {
    return brandRepository.findById(id);
  }

  public List<Brand> getAllBrands () {
    return brandRepository.findAll();
  }

}
