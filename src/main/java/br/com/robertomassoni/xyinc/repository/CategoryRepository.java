package br.com.robertomassoni.xyinc.repository;

import br.com.robertomassoni.xyinc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
        
    public Category findByName(String name);
}
