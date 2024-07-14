package devleopx.coffee.api.item;

import devleopx.coffee.api.ApiTestSupport;
import devleopx.coffee.api.item.request.ItemCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;

import static devleopx.coffee.item.ItemType.ESPRESSO_DRINKS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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


    @DisplayName("[POST] `/api/items` 호출시 201 코드가 내려온다.")
    @Test
    void create() throws Exception {
        // given
        when(itemService.create(any()))
                .thenReturn(1L);

        // given
        ItemCreateRequest request = ItemCreateRequest.builder()
                .itemName("아메리카노")
                .itemType(ESPRESSO_DRINKS)
                .price(4000)
                .build();

        // when
        // then
        mockMvc.perform(post("/api/items")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }


}