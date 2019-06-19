package roman.pidkostelnyi.victoriaarmario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import roman.pidkostelnyi.victoriaarmario.dto.request.OrderRequest;
import roman.pidkostelnyi.victoriaarmario.dto.request.OrderSearchRequest;
import roman.pidkostelnyi.victoriaarmario.dto.request.ProductForOrderRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.OrderResponse;
import roman.pidkostelnyi.victoriaarmario.dto.response.PageResponse;
import roman.pidkostelnyi.victoriaarmario.entity.Order;
import roman.pidkostelnyi.victoriaarmario.entity.ProductForOrder;
import roman.pidkostelnyi.victoriaarmario.repository.OrderRepository;
import roman.pidkostelnyi.victoriaarmario.repository.ProductForOrderRepository;
import roman.pidkostelnyi.victoriaarmario.specification.OrderSpecification;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.KIEV_ZONE;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductForOrderRepository productForOrderRepository;

    @Autowired
    private ProductService productService;

    public void save(OrderRequest request) {
        final Order order = orderRepository.save(orderRequestToOrder(null, request));
        saveProductsForOrder(order, request);
    }

    public PageResponse<OrderResponse> findAll(OrderSearchRequest request) {
        final Page<Order> page = orderRepository.findAll(new OrderSpecification(request), request.getPagination().toPageable());
        return new PageResponse<>(page.get().map(OrderResponse::new).collect(Collectors.toList()), page.getTotalPages(), page.getTotalElements());
    }

    public void update(Long id, OrderRequest request) {
        final Order order = orderRepository.save(orderRequestToOrder(findOne(id), request));
        saveProductsForOrder(order, request);
    }

    public void archive(Long id) {
        final Order order = findOne(id);
        order.setDone(LocalDateTime.now(ZoneId.of(KIEV_ZONE)));
        orderRepository.save(order);
    }

    public void unarchive(Long id) {
        final Order order = findOne(id);
        order.setDone(null);
        orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.delete(findOne(id));
    }

    public Order findOne(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order with id " + id + " not exists"));
    }

    private Order orderRequestToOrder(Order order, OrderRequest request) {
        if (order == null) {
            order = new Order();
            order.setPosted(LocalDateTime.now(ZoneId.of(KIEV_ZONE)));
        }
        order.setEmail(request.getEmail());
        order.setAddress(request.getAddress());
        order.setComment(request.getComment());
        return order;
    }

    private void saveProductsForOrder(Order order, OrderRequest request) {
        productForOrderRepository.deleteAll(order.getProductsForOrder());
        List<ProductForOrder> productsForOrder = request.getProducts().stream().map(p -> productForOrderRequestToProductForOrder(order, p)).collect(Collectors.toList());
        productForOrderRepository.saveAll(productsForOrder);
    }

    private ProductForOrder productForOrderRequestToProductForOrder(Order order, ProductForOrderRequest request) {
        return ProductForOrder.builder()
                .count(request.getCount())
                .order(order)
                .product(productService.findOne(request.getProductId()))
                .build();
    }
}
