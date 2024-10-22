package com.betrybe.exercicio42.controllers;

import com.betrybe.exercicio42.controllers.dto.ProductDTO;
import com.betrybe.exercicio42.controllers.dto.ResponseDTO;
import com.betrybe.exercicio42.models.entities.Product;
import com.betrybe.exercicio42.services.ProductService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

@Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  ResponseEntity<ResponseDTO<Product>> createProduct(@RequestBody ProductDTO productDTO) {
    Product product = productService.insertProduct(productDTO.toProduct());
    ResponseDTO<Product> newProduct = new ResponseDTO<>("Produto criado com sucesso!", product);
    return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
  }

  @PutMapping("/{id}")
  ResponseEntity<ResponseDTO<Product>> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
   Optional<Product> product = productService.updateProduct(id, productDTO.toProduct());
   if (product.isEmpty()){
     ResponseDTO<Product> notFoundProduct = new ResponseDTO<>("Produto não encontrado!", null);
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundProduct);
   }
    ResponseDTO<Product> updatedProduct = new ResponseDTO<>("Produto alterado com sucesso!!", product.get());
    return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ResponseDTO<Product>> removeProduct(@PathVariable Long id) {
    Optional<Product> product = productService.removeProduct(id);
    if (product.isEmpty()){
      ResponseDTO<Product> notFoundProduct = new ResponseDTO<>("Produto não encontrado!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundProduct);
    }
    ResponseDTO<Product> removedProduct = new ResponseDTO<>("Produto removido com sucesso!!", product.get());
    return ResponseEntity.status(HttpStatus.OK).body(removedProduct);
  }

  @GetMapping("/{id}")
  ResponseEntity<ResponseDTO<Product>> getProductById (@PathVariable Long id) {
    Optional<Product> product = productService.getProductByID(id);
    if (product.isEmpty()){
      ResponseDTO<Product> notFoundProduct = new ResponseDTO<>("Produto não encontrado!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundProduct);
    }
    ResponseDTO<Product> foundProduct = new ResponseDTO<>("Produto encontrado com sucesso!!", product.get());
    return ResponseEntity.status(HttpStatus.OK).body(foundProduct);

  }

  @GetMapping
  List<ProductDTO> getAllProducts(){
    List<Product> allProducts = productService.getAllProducts();
    return allProducts.stream().map( (product) -> new ProductDTO(
        product.getId(), product.getName(), product.getPrice()))
        .collect(Collectors.toList());
  }
}
