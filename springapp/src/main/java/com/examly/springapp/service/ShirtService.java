package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.Shirt;

public interface ShirtService {
   
     Shirt createShirt(Shirt shirt);
    List<Shirt> getAllShirts();
    Optional<Shirt> getShirtById(int shirtId);
    Optional<Shirt> updateShirt(int shirtId, Shirt shirtDetails);
    boolean deleteShirt(int shirtId);
    
}
