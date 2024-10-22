package com.betrybe.exercicio42.services;

import com.betrybe.exercicio42.models.entities.Product;
import com.betrybe.exercicio42.models.repositories.ProductRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;
@Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

 public Product insertProduct (Product product ) {
  return productRepository.save(product);
  }
  public Optional<Product> updateProduct (Long id, Product product ) {
    Optional<Product> optionalProduct = productRepository.findById(id);
    if (optionalProduct.isPresent()){
      Product productFromDb = optionalProduct.get();
      productFromDb.setName(product.getName());
      productFromDb.setPrice(product.getPrice());
      Product updatedProduct = productRepository.save(productFromDb);
      return Optional.of(updatedProduct);
    }
    return optionalProduct;
  }

  public Optional<Product> removeProduct (Long id) {
    Optional<Product> optionalProduct = productRepository.findById(id);
    if (optionalProduct.isPresent()){
      productRepository.deleteAllById(Collections.singleton(id));
    }
    return optionalProduct;
  }

  public Optional<Product> getProductByID (Long id) {
  return productRepository.findById(id);
  }

  public List<Product> getAllProducts () {
  return productRepository.findAll();
  }
}
