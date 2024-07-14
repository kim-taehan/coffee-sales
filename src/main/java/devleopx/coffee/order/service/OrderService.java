package devleopx.coffee.order.service;

import devleopx.coffee.api.order.request.OrderCreateRequest;
import devleopx.coffee.order.DiscountPolicy;

import java.util.List;

public interface OrderService {

    void create(List<Long> longs, DiscountPolicy discountPolicy);
}
