package com.example.real.estate.agency.controller;

import com.example.real.estate.agency.entity.Request;
import com.example.real.estate.agency.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestAPIController {
    @Autowired
    private RequestRepository requestRepository;

    @GetMapping
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Optional<Request> request = requestRepository.findById(id);
        return request.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Request> createRealEstateObject(@RequestBody Request request) {
        Request savedObject = requestRepository.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRealEstateObject(@PathVariable Long id, @RequestBody Request updatedObject) {
        Optional<Request> existingObjectOptional = requestRepository.findById(id);
        if (existingObjectOptional.isPresent()) {
            updatedObject.setId(id);
            Request savedObject = requestRepository.save(updatedObject);
            return ResponseEntity.ok(savedObject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRealEstateObject(@PathVariable Long id) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            requestRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
