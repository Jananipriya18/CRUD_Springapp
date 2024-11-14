package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Shirt;
import com.examly.springapp.repository.ShirtRepo;

@Service
public class ShirtServiceImpl implements ShirtService {

@Autowired
private ShirtRepo shirtRepository;


@Override
public Shirt createShirt(Shirt shirt) {
    return shirtRepository.save(shirt);
}


  @Override
    public List<Shirt> getAllShirts() {
        return shirtRepository.findAll();
    }

    @Override
    public Optional<Shirt> getShirtById(int shirtId) {
        return shirtRepository.findById(shirtId);
    }

    @Override
    public Optional<Shirt> updateShirt(int shirtId, Shirt shirtDetails) {
        return shirtRepository.findById(shirtId).map(shirt -> {
            shirt.setShirtSize(shirtDetails.getShirtSize());
            shirt.setShirtColor(shirtDetails.getShirtColor());
            shirt.setShirtPrice(shirtDetails.getShirtPrice());
            shirt.setShirtStyle(shirtDetails.getShirtStyle());
            return shirtRepository.save(shirt);
        });
    }

    @Override
    public boolean deleteShirt(int shirtId) {
        if (shirtRepository.existsById(shirtId)) {
            shirtRepository.deleteById(shirtId);
            return true;
        }
        return false;
    }

    
}
