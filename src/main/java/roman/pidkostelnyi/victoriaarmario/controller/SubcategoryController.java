package roman.pidkostelnyi.victoriaarmario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelnyi.victoriaarmario.dto.request.SubcategoryRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.SubcategoryResponse;
import roman.pidkostelnyi.victoriaarmario.service.SubcategoryService;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.SUBCATEGORY_URL;

@CrossOrigin
@RestController
@RequestMapping(SUBCATEGORY_URL)
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @PostMapping
    public void create(@Valid @RequestBody SubcategoryRequest request) throws IOException {
        subcategoryService.save(request);
    }

    @GetMapping
    public List<SubcategoryResponse> findAllByCategoryId(Long categoryId) {
        return subcategoryService.findAllByCategoryId(categoryId);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody SubcategoryRequest request) throws IOException {
        subcategoryService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        subcategoryService.delete(id);
    }
}
