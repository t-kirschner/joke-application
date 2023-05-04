package com.sqs.jokeme;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:5173/")
public class Controller {
    Api api = new Api();

    @PostMapping("/")
    public ResponseEntity<String> processData(@RequestBody String data) {
        String joke = api.getApiResponse(data);
        System.out.println(joke);

        return ResponseEntity.ok("We can talk!");
    }
}
