package roman.pidkostelnyi.victoriaarmario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelnyi.victoriaarmario.dto.request.OrderRequest;
import roman.pidkostelnyi.victoriaarmario.dto.request.OrderSearchRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.OrderResponse;
import roman.pidkostelnyi.victoriaarmario.dto.response.PageResponse;
import roman.pidkostelnyi.victoriaarmario.service.OrderService;

import javax.validation.Valid;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.ORDER_URL;

@CrossOrigin
@RestController
@RequestMapping(ORDER_URL)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void create(@Valid @RequestBody OrderRequest request) {
        orderService.save(request);
    }

    @PostMapping("/page")
    public PageResponse<OrderResponse> findAll(OrderSearchRequest request) {
        return orderService.findAll(request);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody OrderRequest request) {
        orderService.update(id, request);
    }

    @PutMapping("/archive")
    public void archive(Long id) {
        orderService.archive(id);
    }

    @PutMapping("/unarchive")
    public void unarchive(Long id) {
        orderService.unarchive(id);
    }

    @DeleteMapping
    public void delete(Long id) {
        orderService.delete(id);
    }
}
