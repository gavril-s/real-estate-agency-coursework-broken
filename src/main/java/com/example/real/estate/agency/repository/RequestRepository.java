package com.example.real.estate.agency.repository;

import com.example.real.estate.agency.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findRequestsByObjectId(Long objectId);
}
