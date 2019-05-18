package roman.pidkostelnyi.victoriaarmario.specification;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import roman.pidkostelnyi.victoriaarmario.dto.request.OrderSearchRequest;
import roman.pidkostelnyi.victoriaarmario.entity.Order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OrderSpecification implements Specification<Order> {

    private Long minSum;
    private Long maxSum;

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    public OrderSpecification(OrderSearchRequest request) {
        minSum = request.getMinSum();
        maxSum = request.getMaxSum();
        dateFrom = request.getDateFrom();
        dateTo = request.getDateTo();
    }

    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(findBySum(root, criteriaBuilder));
        predicates.add(findByDate(root, criteriaBuilder));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findBySum(Root<Order> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (maxSum == null && minSum == null) {
            predicate = cb.conjunction();
        } else if (maxSum == null) {
            predicate = cb.greaterThanOrEqualTo(r.get("sum"), minSum);
        } else if (minSum == null) {
            predicate = cb.lessThanOrEqualTo(r.get("sum"), maxSum);
        } else {
            predicate = cb.between(r.get("sum"), minSum, maxSum);
        }
        return predicate;
    }

    private Predicate findByDate(Root<Order> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (dateFrom == null && dateTo == null) {
            predicate = cb.conjunction();
        } else if (dateTo == null) {
            predicate = cb.greaterThanOrEqualTo(r.get("date"), dateFrom);
        } else if (dateFrom == null) {
            predicate = cb.lessThanOrEqualTo(r.get("date"), dateTo);
        } else {
            predicate = cb.between(r.get("date"), dateFrom, dateTo);
        }
        return predicate;
//        return cb.equal(r.get("date"), dateTo);
    }

}
