package com.betrybe.exercicio42.models.repositories;

import com.betrybe.exercicio42.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {

}
