package com.example.shoppingcart;

import com.example.shoppingcart.Models.CartItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CartItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddCartItem() throws Exception {
        CartItem cartItem = new CartItem(1L, "Product A", 10.99);

        mockMvc.perform(MockMvcRequestBuilders.post("/cartitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteCartItem() throws Exception {
        CartItem cartItem = new CartItem(1L, "Product A", 10.99);

        mockMvc.perform(MockMvcRequestBuilders.post("/cartitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.delete("/cartitems/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCartItemForNonExistingId() throws Exception {
        // Add a cart item for testing
        CartItem cartItem = new CartItem(1L, "Product A", 10.99);
        mockMvc.perform(MockMvcRequestBuilders.post("/cartitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isOk());

        // Verify that the cart item exists
        mockMvc.perform(MockMvcRequestBuilders.get("/cartitems/1"))
                .andExpect(status().isOk());

        // Remove the cart item
        mockMvc.perform(MockMvcRequestBuilders.delete("/cartitems/1"))
                .andExpect(status().isOk());

        // Verify that the cart item has been removed
        mockMvc.perform(MockMvcRequestBuilders.get("/cartitems/1"))
                .andExpect(status().isNotFound());

        // Try removing a non-existing cart item
        mockMvc.perform(MockMvcRequestBuilders.delete("/cartitems/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllCartItems() throws Exception {

        // Add some cart items for testing
        CartItem item1 = new CartItem(1L, "Product A", 10.99);
        CartItem item2 = new CartItem(2L, "Product B", 20.49);
        mockMvc.perform(MockMvcRequestBuilders.post("/cartitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item1)))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/cartitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item2)))
                .andExpect(status().isOk());

        // Perform GET request to retrieve all cart items
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cartitems"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Parse response body
        String responseBody = result.getResponse().getContentAsString();
        List<CartItem> cartItems = objectMapper.readValue(responseBody, new TypeReference<List<CartItem>>() {});

        // Verify the content of the response
        assertNotNull(cartItems);
        assertEquals(2, cartItems.size());
        assertEquals(item1.getId(), cartItems.get(0).getId());
        assertEquals(item1.getName(), cartItems.get(0).getName());
        assertEquals(item1.getPrice(), cartItems.get(0).getPrice(), 0.001);
        assertEquals(item2.getId(), cartItems.get(1).getId());
        assertEquals(item2.getName(), cartItems.get(1).getName());
        assertEquals(item2.getPrice(), cartItems.get(1).getPrice(), 0.001);
    }
}
