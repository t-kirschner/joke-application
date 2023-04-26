package com.sqs.jokeme;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:5173/")
public class GreetingController {

    @PostMapping("/")
    public ResponseEntity<String> processData(@RequestBody String data) {
        System.out.println(data);

        return ResponseEntity.ok("We can talk!");
    }
}
