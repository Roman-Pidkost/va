package roman.pidkostelnyi.victoriaarmario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roman.pidkostelnyi.victoriaarmario.dto.request.ProductCriteriaRequest;
import roman.pidkostelnyi.victoriaarmario.dto.request.ProductRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.PageResponse;
import roman.pidkostelnyi.victoriaarmario.dto.response.ProductFindOneResponse;
import roman.pidkostelnyi.victoriaarmario.dto.response.ProductResponse;
import roman.pidkostelnyi.victoriaarmario.entity.Product;
import roman.pidkostelnyi.victoriaarmario.repository.ProductRepository;
import roman.pidkostelnyi.victoriaarmario.specification.ProductSpecification;
import roman.pidkostelnyi.victoriaarmario.tool.FileTool;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private FileTool fileTool;

    @Value("${products.img.directory}")
    private String imgDirectory;

    public void create(ProductRequest request) {
        productRepository.save(productRequestToProduct(null, request));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    public PageResponse<ProductResponse> findAll(ProductCriteriaRequest request) {
        Page<Product> page = productRepository.findAll(new ProductSpecification(request), request.getPaginationRequest().toPageable());
        return new PageResponse<>(page.get().map(ProductResponse::new).collect(Collectors.toList()), page.getTotalPages(), page.getTotalElements());
    }

    @Transactional
    public ProductFindOneResponse findOneResponse(Long id) {
        return new ProductFindOneResponse(findOne(id));
    }

    public void update(Long id, ProductRequest request) {
        productRepository.save(productRequestToProduct(findOne(id), request));
    }

    public void updateMainImage(Long id, String imageName) {
        Product product = findOne(id);
        product.setMainImage(imageName);
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(findOne(id));
    }

    public Product findOne(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " not exists"));
    }

    private Product productRequestToProduct(Product product, ProductRequest request) {
        if (product == null) {
            product = new Product();
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCatalogNumber(request.getCatalogNumber());
        product.setPrice(request.getPrice());
        product.setSubcategory(subcategoryService.findOne(request.getSubcategoryId()));
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            product.setImages(request.getImages().stream().map(this::saveImage).collect(Collectors.toList()));
            product.setMainImage(null);
        }
        if (request.getColorsIds() != null && !request.getColorsIds().isEmpty()) {
            product.setColors(request.getColorsIds().stream().map(colorService::findOne).collect(Collectors.toList()));
        }
        return product;
    }

    private String saveImage(String image) {
        try {
            return fileTool.saveFile(image, imgDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
