package com.betrybe.exercicio42.controllers;

import com.betrybe.exercicio42.controllers.dto.BrandDTO;
import com.betrybe.exercicio42.controllers.dto.ResponseDTO;
import com.betrybe.exercicio42.models.entities.Brand;
import com.betrybe.exercicio42.services.BrandService;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/brands")
public class BrandController {

  private final BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @PostMapping
  ResponseEntity<ResponseDTO<Brand>> createBrand(@RequestBody BrandDTO brandDTO) {
    Brand brand = brandService.insertBrand(brandDTO.toBrand());
    ResponseDTO<Brand> newBrand = new ResponseDTO<>("Marca criada com sucesso!", brand);
    return ResponseEntity.status(HttpStatus.CREATED).body(newBrand);
  }

  @PutMapping("/{id}")
  ResponseEntity<ResponseDTO<Brand>> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
    Optional<Brand> brand = brandService.updateBrand(id, brandDTO.toBrand());
    if (brand.isEmpty()) {
      ResponseDTO<Brand> notFoundBrand = new ResponseDTO<>("Marca não encontrada!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundBrand);
    }
    ResponseDTO<Brand> updatedBrand = new ResponseDTO<>("Marca alterada com sucesso!!", brand.get());
    return ResponseEntity.status(HttpStatus.OK).body(updatedBrand);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ResponseDTO<Brand>> removeBrand(@PathVariable Long id) {
    Optional<Brand> brand = brandService.removeBrand(id);
    if (brand.isEmpty()) {
      ResponseDTO<Brand> notFoundBrand = new ResponseDTO<>("Marca não encontrada!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundBrand);
    }
    ResponseDTO<Brand> removedBrand = new ResponseDTO<>("Marca removida com sucesso!!", brand.get());
    return ResponseEntity.status(HttpStatus.OK).body(removedBrand);
  }

  @GetMapping("/{id}")
  ResponseEntity<ResponseDTO<Brand>> getBrandById(@PathVariable Long id) {
    Optional<Brand> brand = brandService.getBrandByID(id);
    if (brand.isEmpty()) {
      ResponseDTO<Brand> notFoundBrand = new ResponseDTO<>("Marca não encontrada!", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundBrand);
    }
    ResponseDTO<Brand> foundBrand = new ResponseDTO<>("Marca encontrada com sucesso!!", brand.get());
    return ResponseEntity.status(HttpStatus.OK).body(foundBrand);
  }

  @GetMapping
  List<BrandDTO> getAllBrands() {
    List<Brand> allBrands = brandService.getAllBrands();
    return allBrands.stream().map((brand) -> new BrandDTO(
            brand.getId(), brand.getName()))
        .collect(Collectors.toList());
  }
}
