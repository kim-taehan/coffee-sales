package devleopx.coffee.order.service;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.service.ItemService;
import devleopx.coffee.order.DiscountPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ItemService itemService;

    @Override
    public void create(List<Long> itemIds, DiscountPolicy discountPolicy) {

        discountPolicy = Objects.isNull(discountPolicy) ? DiscountPolicy.defaultedPolicy() : discountPolicy;

        Integer totalAmount = 0;
        for (Long itemId : itemIds) {
            Item item = itemService.find(itemId);
            totalAmount += item.getPrice();
        }
        int discount = discountPolicy.discount(totalAmount);
        String orderNo = UUID.randomUUID().toString();



    }
}
