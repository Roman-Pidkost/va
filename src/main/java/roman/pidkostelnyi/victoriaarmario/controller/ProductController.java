package roman.pidkostelnyi.victoriaarmario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelnyi.victoriaarmario.dto.request.ProductCriteriaRequest;
import roman.pidkostelnyi.victoriaarmario.dto.request.ProductRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.PageResponse;
import roman.pidkostelnyi.victoriaarmario.dto.response.ProductResponse;
import roman.pidkostelnyi.victoriaarmario.service.ProductService;

import javax.validation.Valid;

import java.util.List;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.PRODUCT_URL;

@CrossOrigin
@RestController
@RequestMapping(PRODUCT_URL)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void create(@Valid @RequestBody ProductRequest request) {
        productService.create(request);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @GetMapping("/page")
    public PageResponse<ProductResponse> findAllByCriteria(ProductCriteriaRequest request) {
        return productService.findAll(request);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody ProductRequest request) {
        productService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        productService.delete(id);
    }
}
