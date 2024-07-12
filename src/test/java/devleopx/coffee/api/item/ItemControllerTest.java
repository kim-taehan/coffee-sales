package devleopx.coffee.api.item;

import devleopx.coffee.api.ApiTestSupport;
import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends ApiTestSupport {

    @DisplayName("[GET] `/api/items` 호출시 200 코드가 내려온다.")
    @Test
    void findItems() throws Exception {
        // given
        when(itemService.findItems(any(), any()))
                .thenReturn(new ArrayList<>());

        // when
        // then
        mockMvc.perform(get("/api/items")
                .queryParam("itemType", "ESPRESSO_DRINKS")
                .queryParam("itemName", ""))
                .andExpect(status().isOk());
    }

}