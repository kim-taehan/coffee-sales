package devleopx.coffee.api.item;

import devleopx.coffee.api.item.response.ItemFindResponse;
import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import devleopx.coffee.item.service.ItemService;
import devleopx.coffee.item.service.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // find, create, modify, remove
    // select, insert, update, delete
    @GetMapping("")
    public ResponseEntity<List<ItemFindResponse>> findItems(String itemName, ItemType itemType) {
        List<ItemFindResponse> resultList = itemService.findItems(itemType, itemName)
                .stream()
                .map(ItemFindResponse::from)
                .toList();
        return ResponseEntity.ok(resultList);
    }
}
