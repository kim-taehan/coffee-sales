package devleopx.coffee.api.item;

import devleopx.coffee.api.item.request.ItemCreateRequest;
import devleopx.coffee.api.item.response.ItemFindResponse;
import devleopx.coffee.item.ItemType;
import devleopx.coffee.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // find, create, modify, remove
    // select, insert, update, delete
    @GetMapping("")
    public ResponseEntity<List<ItemFindResponse>> findItems(ItemType itemType, String itemName) {
        List<ItemFindResponse> resultList = itemService.findItems(itemType, itemName)
                .stream()
                .map(ItemFindResponse::of)
                .toList();
        return ResponseEntity.ok(resultList);
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ItemCreateRequest request) {
        Long itemId = itemService.create(request.convertItem());
        return ResponseEntity.status(HttpStatus.CREATED).body(itemId);
    }
}
