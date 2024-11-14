package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.io.File;
import java.lang.reflect.Method;


@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShirtShippingLabelApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // File and Folder Existence Tests
    @Test
    @Order(1)
    void testControllerFolder() {
        String directoryPath = "src/main/java/com/examly/springapp/controller";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    @Order(2)
    void testShirtControllerFile() {
        String filePath = "src/main/java/com/examly/springapp/controller/ShirtController.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(3)
    void testModelFolder() {
        String directoryPath = "src/main/java/com/examly/springapp/model";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    @Order(4)
    void testShirtFile() {
        String filePath = "src/main/java/com/examly/springapp/model/Shirt.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(5)
    void testRepositoryFolder() {
        String directoryPath = "src/main/java/com/examly/springapp/repository";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    @Order(6)
    void testShirtRepoFile() {
        String filePath = "src/main/java/com/examly/springapp/repository/ShirtRepo.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(7)
    void testServiceFolder() {
        String directoryPath = "src/main/java/com/examly/springapp/service";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    @Order(8)
    void testShirtServiceFile() {
        String filePath = "src/main/java/com/examly/springapp/service/ShirtService.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(9)
    void testShirtServiceImplFile() {
        String filePath = "src/main/java/com/examly/springapp/service/ShirtServiceImpl.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    // Method Existence Test for ShirtService Interface
   

    @Test
    @Order(10)
    void testShirtServiceInterfaceMethods() {
        String[] expectedMethodNames = {
            "createShirt",
            "getAllShirts",
            "getShirtById",
            "updateShirt",
            "deleteShirt"
        };
    
        try {
            Class<?> shirtServiceClass = Class.forName("com.examly.springapp.service.ShirtService");
    
            for (String methodName : expectedMethodNames) {
                boolean methodExists = false;
                Method[] methods = shirtServiceClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getName().equals(methodName)) {
                        methodExists = true;
                        break;
                    }
                }
                assertTrue(methodExists, "Method " + methodName + " should exist in ShirtService interface.");
            }
    
            Method createShirtMethod = shirtServiceClass.getMethod("createShirt", Class.forName("com.examly.springapp.model.Shirt"));
            assertNotNull(createShirtMethod);
    
            Method getAllShirtsMethod = shirtServiceClass.getMethod("getAllShirts");
            assertNotNull(getAllShirtsMethod);
    
            Method getShirtByIdMethod = shirtServiceClass.getMethod("getShirtById", int.class);
            assertNotNull(getShirtByIdMethod);
    
            Method updateShirtMethod = shirtServiceClass.getMethod("updateShirt", int.class, Class.forName("com.examly.springapp.model.Shirt"));
            assertNotNull(updateShirtMethod);
    
            Method deleteShirtMethod = shirtServiceClass.getMethod("deleteShirt", int.class);
            assertNotNull(deleteShirtMethod);
    
        } catch (ClassNotFoundException e) {
            fail("ShirtService interface not found: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            fail("One of the methods does not exist: " + e.getMessage());
        }
    }
    


    @Test
    @Order(11)
    void testAddShirt() throws Exception {
        String shirtJson = "{ \"shirtId\": 1, \"shirtSize\": 42, \"shirtColor\": \"Blue\", \"shirtPrice\": 29.99, \"shirtStyle\": \"Casual\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/shirt")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shirtJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.shirtId").value(1))
                .andExpect(jsonPath("$.shirtSize").value(42))
                .andExpect(jsonPath("$.shirtColor").value("Blue"))
                .andExpect(jsonPath("$.shirtPrice").value(29.99))
                .andExpect(jsonPath("$.shirtStyle").value("Casual"));
    }

    @Test
    @Order(12)
    void testGetShirtById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/shirt/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shirtId").value(1))
                .andExpect(jsonPath("$.shirtSize").value(42))
                .andExpect(jsonPath("$.shirtColor").value("Blue"))
                .andExpect(jsonPath("$.shirtPrice").value(29.99))
                .andExpect(jsonPath("$.shirtStyle").value("Casual"));
    }

    @Test
    @Order(13)
    void testGetAllShirts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/shirt")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.shirtColor == 'Blue')]").exists());
    }

    @Test
    @Order(14)
    void testUpdateShirt() throws Exception {
        String shirtJson = "{ \"shirtId\": 1, \"shirtSize\": 44, \"shirtColor\": \"Black\", \"shirtPrice\": 19.99, \"shirtStyle\": \"Cotton\" }";

        mockMvc.perform(MockMvcRequestBuilders.put("/shirt/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shirtJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shirtId").value(1))
                .andExpect(jsonPath("$.shirtSize").value(44))
                .andExpect(jsonPath("$.shirtColor").value("Black"))
                .andExpect(jsonPath("$.shirtPrice").value(19.99))
                .andExpect(jsonPath("$.shirtStyle").value("Cotton"));
    }

    @Test
    @Order(15)
    void testDeleteShirt() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/shirt/1"))
                .andExpect(status().isNoContent());
    }
}
