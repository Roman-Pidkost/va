package roman.pidkostelnyi.victoriaarmario.specification;

import org.springframework.data.jpa.domain.Specification;
import roman.pidkostelnyi.victoriaarmario.dto.request.ProductCriteriaRequest;
import roman.pidkostelnyi.victoriaarmario.entity.Category;
import roman.pidkostelnyi.victoriaarmario.entity.Product;
import roman.pidkostelnyi.victoriaarmario.entity.Subcategory;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.*;

public class ProductSpecification implements Specification<Product> {

    private String value;
    private Integer minPrice;
    private Integer maxPrice;
    private List<Long> colorsId;
    private Long categoryId;
    private Long subcategoryId;

    public ProductSpecification(ProductCriteriaRequest request) {
        value = request.getValue();
        minPrice = request.getMinPrice();
        maxPrice = request.getMaxPrice();
        colorsId = request.getColorsId();
        categoryId = request.getCategoryId();
        subcategoryId = request.getSubcategoryId();
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();
        predicates.add(findByNameLike(root, criteriaBuilder));
        predicates.add(findByColors(root, criteriaBuilder));
        predicates.add(findByPrice(root, criteriaBuilder));
        predicates.add(findByCategoryAndSubcategory(root, criteriaBuilder));
        return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    }

    private Predicate findByNameLike(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (value == null || value.isEmpty()) {
            predicate = cb.conjunction();
        } else {
            predicate = cb.like(r.get(FIELD_NAME), PERCENT + value + PERCENT);
        }
        return predicate;
    }

    private Predicate findByCategoryAndSubcategory(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;

        final Join<Product, Subcategory> subcategoryJoin = r.join(FIELD_SUBCATEGORY);
        final Join<Subcategory, Category> categoryJoin = subcategoryJoin.join(FIELD_CATEGORY);

        if (subcategoryId != null) {
            predicate = cb.equal(subcategoryJoin.get("id"), subcategoryId);
        } else if (categoryId != null) {
            predicate = cb.equal(categoryJoin.get("id"), categoryId);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByColors(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (colorsId == null || colorsId.isEmpty()) {
            predicate = cb.conjunction();
        } else {
            predicate = r.get(FIELD_COLORS).in(colorsId);
        }
        return predicate;
    }

    private Predicate findByPrice(Root<Product> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (maxPrice == null && minPrice == null) {
            predicate = cb.conjunction();
        } else if (maxPrice == null) {
            predicate = cb.greaterThanOrEqualTo(r.get(FIELD_PRICE), minPrice);
        } else if (minPrice == null) {
            predicate = cb.lessThanOrEqualTo(r.get(FIELD_PRICE), maxPrice);
        } else {
            predicate = cb.between(r.get(FIELD_PRICE), minPrice, maxPrice);
        }
        return predicate;
    }
}
