package devleopx.coffee.api.order;

import devleopx.coffee.api.order.request.OrderCreateRequest;
import devleopx.coffee.order.DiscountPolicy;
import devleopx.coffee.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody OrderCreateRequest request) {
        orderService.create(request.itemIds(), request.discountPolicy());
        return ResponseEntity.ok(10L);
    }

}
