package com.examly.springapp.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Shirt;
import com.examly.springapp.service.ShirtService;

@RestController
@RequestMapping("/shirt")
public class ShirtController {


    @Autowired
    private ShirtService shirtService;

    // POST /shirt - Create a new shirt
    @PostMapping
    public ResponseEntity<Shirt> createShirt(@RequestBody Shirt shirt) {
        try {
            Shirt createdShirt = shirtService.createShirt(shirt);
            return new ResponseEntity<>(createdShirt, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET /shirt - Get list of all shirts
   
    @GetMapping()
    public ResponseEntity<List<Shirt>> getAllShirts() {
        List<Shirt> shirts = shirtService.getAllShirts();
        if (shirts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shirts, HttpStatus.OK);
    }



    // GET /shirt/{shirtId} - Get shirt by ID
    @GetMapping("/{shirtId}")
    public ResponseEntity<Shirt> getShirtById(@PathVariable int shirtId) {
        Optional<Shirt> shirtData = shirtService.getShirtById(shirtId);

        return shirtData.map(shirt -> new ResponseEntity<>(shirt, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT /shirt/{shirtId} - Update shirt by ID
    @PutMapping("/{shirtId}")
    public ResponseEntity<Shirt> updateShirt(@PathVariable int shirtId, @RequestBody Shirt shirtDetails) {
        Optional<Shirt> updatedShirt = shirtService.updateShirt(shirtId, shirtDetails);

        return updatedShirt.map(shirt -> new ResponseEntity<>(shirt, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE /shirt/{shirtId} - Delete shirt by ID
    @DeleteMapping("/{shirtId}")
    public ResponseEntity<HttpStatus> deleteShirt(@PathVariable int shirtId) {
        try {
            if (shirtService.deleteShirt(shirtId)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
}
