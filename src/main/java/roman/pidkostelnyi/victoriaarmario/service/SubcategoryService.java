package roman.pidkostelnyi.victoriaarmario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roman.pidkostelnyi.victoriaarmario.dto.request.SubcategoryRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.SubcategoryResponse;
import roman.pidkostelnyi.victoriaarmario.entity.Subcategory;
import roman.pidkostelnyi.victoriaarmario.repository.SubcategoryRepository;
import roman.pidkostelnyi.victoriaarmario.tool.FileTool;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private FileTool fileTool;

    @Value("${subcategories.img.directory}")
    private String imgDirectory;

    public void save(SubcategoryRequest request) throws IOException {
        subcategoryRepository.save(subcategoryRequestToSubcategory(null, request));
    }

    public List<SubcategoryResponse> findAll() {
        return subcategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "category.name")).stream().map(SubcategoryResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public List<SubcategoryResponse> findAllByCategory(Long categoryId) {
        return subcategoryRepository.findAllByCategoryId(categoryId).map(SubcategoryResponse::new).collect(Collectors.toList());
    }

    public void update(Long id, SubcategoryRequest request) throws IOException {
        subcategoryRepository.save(subcategoryRequestToSubcategory(findOne(id), request));
    }

    public void delete(Long id) {
        Subcategory subcategory = findOne(id);
        if (subcategory.getProducts().isEmpty()) {
            subcategoryRepository.delete(subcategory);
            fileTool.deleteFile(imgDirectory, subcategory.getImage());
        } else {
            throw new IllegalArgumentException("Subcategory cannot be deleted");
        }
    }

    public Subcategory findOne(Long id) {
        return subcategoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Subcategory with id " + id + " not exists"));
    }

    private Subcategory subcategoryRequestToSubcategory(Subcategory subcategory, SubcategoryRequest request) throws IOException {
        if (subcategory == null) {
            subcategory = new Subcategory();
        }
        subcategory.setName(request.getName());
        subcategory.setCategory(categoryService.findOne(request.getCategoryId()));
        if (request.getImage() != null) {
            subcategory.setImage(fileTool.saveFile(request.getImage(), imgDirectory));
        }
        return subcategory;
    }
}
