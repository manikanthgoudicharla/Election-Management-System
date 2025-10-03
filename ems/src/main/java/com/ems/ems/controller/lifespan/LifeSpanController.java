package com.ems.ems.controller.lifespan;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/lifespan")
public class LifeSpanController {

    @GetMapping
    public ResponseEntity<String> CheckLifespanStatus(){
        return ResponseEntity.status(HttpStatus.OK).body("Lifespan Status is Ok");
    }
    
}
