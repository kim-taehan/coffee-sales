package devleopx.coffee.infrastructure;


import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "orders")
public class ItemEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String itemName;

    private ItemType itemType;

    private Integer price;

    public Item convertItem() {
        return Item.builder()
                .itemId(id)
                .price(price)
                .itemName(itemName)
                .itemType(itemType)
                .build();
    }

    @Builder
    public ItemEntity(Long id, String itemName, ItemType itemType, Integer price) {
        this.id = id;
        this.itemName = itemName;
        this.itemType = itemType;
        this.price = price;
    }

    public static ItemEntity of(Item item) {
        return ItemEntity.builder()
                .itemType(item.getItemType())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .build();
    }

    public void update(Item item) {
        this.itemName = item.getItemName();
        this.itemType = item.getItemType();
        this.price = item.getPrice();
    }
}