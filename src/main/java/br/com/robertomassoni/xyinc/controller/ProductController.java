package br.com.robertomassoni.xyinc.controller;

import br.com.robertomassoni.xyinc.model.Product;
import br.com.robertomassoni.xyinc.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;
    
    @GetMapping()
    public String list(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product-list";
    }
    
    @GetMapping("/{id}")
    public String get(Model model, @PathVariable("id") String id) {
        Product product = productRepository.getOne(Integer.parseInt(id));
        model.addAttribute("product", product);
        return "product";
    }
        
}
