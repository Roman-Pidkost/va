package roman.pidkostelnyi.victoriaarmario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import roman.pidkostelnyi.victoriaarmario.dto.request.CategoryRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.CategoryResponse;
import roman.pidkostelnyi.victoriaarmario.entity.Category;
import roman.pidkostelnyi.victoriaarmario.repository.CategoryRepository;
import roman.pidkostelnyi.victoriaarmario.tool.Constants;
import roman.pidkostelnyi.victoriaarmario.tool.FileTool;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.USER_HOME;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileTool fileTool;

    @Value("${categories.img.directory}")
    private String imgDirectory;

    public void save(CategoryRequest request) throws IOException {
        categoryRepository.save(categoryRequestToCategory(null, request));
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll(Sort.by(Constants.FIELD_NAME)).stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

    public void update(Long id, CategoryRequest request) throws IOException {
        categoryRepository.save(categoryRequestToCategory(findOne(id), request));
    }

    public void delete(Long id) {
        Category category = findOne(id);
        categoryRepository.delete(category);
        Paths.get(System.getProperty(USER_HOME), imgDirectory, category.getImage()).toFile().delete();
    }

    public Category findOne(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category with id " + id + " not exists"));
    }

    private Category categoryRequestToCategory(Category category, CategoryRequest request) throws IOException {
        if (category == null) {
            category = new Category();
        }
        category.setName(request.getName());
        if (category.getImage() != null) {
            category.setImage(fileTool.saveFile(request.getImage(), imgDirectory));
        }
        return category;
    }
}
