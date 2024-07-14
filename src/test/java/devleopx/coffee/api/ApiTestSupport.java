package devleopx.coffee.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import devleopx.coffee.api.item.ItemController;
import devleopx.coffee.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {ItemController.class})
public class ApiTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected ItemService itemService;

    protected final ObjectMapper objectMapper = new ObjectMapper();
}
