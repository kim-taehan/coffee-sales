package devleopx.coffee.api.item;

import devleopx.coffee.api.item.response.ItemFindResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    // find, create, modify, remove
    // select, insert, update, delete
    @GetMapping("/2")
    public ResponseEntity<ItemFindResponse> findItems() {
        return ResponseEntity.ok(ItemFindResponse.builder()
                .itemId(100L)
                .itemName("americano")
                .price(3_000)
                .build());
    }

    @GetMapping("/3")
    public String findItem2s() {
        return "test";
    }
    // The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler
    //
}
