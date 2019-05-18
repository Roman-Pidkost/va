package roman.pidkostelnyi.victoriaarmario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelnyi.victoriaarmario.dto.request.CategoryRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.CategoryResponse;
import roman.pidkostelnyi.victoriaarmario.service.CategoryService;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.CATEGORY_URL;

@CrossOrigin
@RestController
@RequestMapping(CATEGORY_URL)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void create(@Valid @RequestBody CategoryRequest request) throws IOException {
        categoryService.save(request);
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody CategoryRequest request) throws IOException {
        categoryService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        categoryService.delete(id);
    }
}
