package com.example.real.estate.agency.controller;

import com.example.real.estate.agency.entity.RealEstateObject;
import com.example.real.estate.agency.entity.RealEstateObjectDTO;
import com.example.real.estate.agency.entity.User;
import com.example.real.estate.agency.entity.Request;
import com.example.real.estate.agency.repository.RealEstateObjectRepository;
import com.example.real.estate.agency.repository.RequestRepository;
import com.example.real.estate.agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminInterfaceController {
    @Autowired
    private RealEstateObjectRepository realEstateObjectRepository;
    @Autowired
    private RequestRepository requestsRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/objects")
    public String objects(Model model) {
        List<RealEstateObject> objects = realEstateObjectRepository.findAll();
        List<Request> requests =  requestsRepository.findAll();

        List<RealEstateObjectDTO> dtoObjects = new ArrayList<>();
        for (RealEstateObject object : objects) {
            RealEstateObjectDTO dtoObject = new RealEstateObjectDTO(object, new ArrayList<>());
            for (Request request : requests) {
                if (request.getObjectId() == object.getId()) {
                    User user = userRepository.getUserById(request.getUserId());
                    dtoObject.addRequestUser(user);
                }
            }
            dtoObjects.add(dtoObject);
        }

        model.addAttribute("objects", dtoObjects);
        return "admin-objects";
    }

    @GetMapping("/admin/new-object")
    public String newObject() {
        return "new-object";
    }
}
