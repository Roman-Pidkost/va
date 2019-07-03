package roman.pidkostelnyi.victoriaarmario.specification;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import roman.pidkostelnyi.victoriaarmario.dto.request.OrderSearchRequest;
import roman.pidkostelnyi.victoriaarmario.entity.Order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.PERCENT;

@AllArgsConstructor
public class OrderSpecification implements Specification<Order> {

    private Long minSum;
    private Long maxSum;

    private Date dateFrom;
    private Date dateTo;

    private String value;

    private Boolean done;

    public OrderSpecification(OrderSearchRequest request) {
        minSum = request.getMinSum();
        maxSum = request.getMaxSum();
        dateFrom = request.getDateFrom();
        dateTo = request.getDateTo();
        done = request.getDone();
        value = request.getValue();
    }

    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(findBySum(root, criteriaBuilder));
        predicates.add(findByDate(root, criteriaBuilder));
        predicates.add(findByNameOrPhoneNumberOrAddressOrEmail(root, criteriaBuilder));
        predicates.add(findByDone(root, criteriaBuilder));
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
    }

    private Predicate findByNameOrPhoneNumberOrAddressOrEmail(Root<Order> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (value != null && !value.trim().isEmpty()) {
            return cb.or(
                    cb.like(r.get("name"), PERCENT + value.trim() + PERCENT),
                    cb.like(r.get("phoneNumber"), PERCENT + value.trim() + PERCENT),
                    cb.like(r.get("email"), PERCENT + value.trim() + PERCENT),
                    cb.like(r.get("address"), PERCENT + value.trim() + PERCENT)
            );
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByDone(Root<Order> r, CriteriaBuilder cb) {
        Predicate predicate;
        if (done != null) {
            return done ? cb.isNotNull(r.get("done")) : cb.isNull(r.get("done"));
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

}
