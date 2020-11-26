package br.com.robertomassoni.xyinc.repository;

import br.com.robertomassoni.xyinc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    
}
