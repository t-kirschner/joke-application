package com.sqs.jokeme;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:5173/")
public class Controller {
    Api api = new Api();

    @PostMapping("/")
    public String processData(@RequestBody String data) {


        return api.getApiResponse(data);
    }
}
