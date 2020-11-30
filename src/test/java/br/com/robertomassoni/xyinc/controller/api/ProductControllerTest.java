package br.com.robertomassoni.xyinc.controller.api;

import java.net.URI;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String token = null;
    private static Integer newProductId = null;

    @Test
    public void testAwrongCredentials() throws Exception {
        URI uri = new URI("/api/auth");
        String json = "{\"user\":\"invalid@email.com\",\"password\":\"12345\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void testBcorrectCredentials() throws Exception {
        URI uri = new URI("/api/auth");
        String json = "{\"user\":\"robertopmassoni@gmail.com\",\"password\":\"12345\"}";

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        String resultString = result.andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(resultString);
        JSONObject content = jsonObject.getJSONObject("content");
        ProductControllerTest.token = content.getString("token");

    }
    
    @Test
    public void testCaddProduct() throws Exception {
        URI uri = new URI("/api/products");
        String json = "{\"name\":\"Book\",\"category\":\"School\",\"price\":\"1.99\"}";
        
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders                
                .post(uri).header("authorization", "Bearer " + ProductControllerTest.token)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        String resultString = result.andReturn().getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(resultString);
        JSONObject content = jsonObject.getJSONObject("content");
        ProductControllerTest.newProductId = content.getInt("id");        
    }
    
    @Test
    public void testDeditProduct() throws Exception {
        URI uri = new URI("/api/products/" + ProductControllerTest.newProductId);
        String json = "{\"name\":\"Book\",\"category\":\"School\",\"price\":\"1.99\"}";
        
        mockMvc.perform(MockMvcRequestBuilders
                .put(uri).header("authorization", "Bearer " + ProductControllerTest.token)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testEgetAllProducts() throws Exception {
        URI uri = new URI("/api/products");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testFgetOneProduct() throws Exception {
        URI uri = new URI("/api/products/" + ProductControllerTest.newProductId);

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testGdeleteProduct() throws Exception {
        URI uri = new URI("/api/products/" + ProductControllerTest.newProductId);

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri).header("authorization", "Bearer " + ProductControllerTest.token))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testHdeleteNonExistentProduct() throws Exception {
        URI uri = new URI("/api/products/9999");

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri).header("authorization", "Bearer " + ProductControllerTest.token))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

}
