package co.coinvestor.xa2pctransactiontest.controller;

import co.coinvestor.xa2pctransactiontest.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping("/test1")
    public void test1(){
        testService.test1();
    }
}
