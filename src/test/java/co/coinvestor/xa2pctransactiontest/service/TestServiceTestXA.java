package co.coinvestor.xa2pctransactiontest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("at")
class TestServiceTestXA {

    @Autowired
    private TestService testService;

    @Test
    public void test1(){
        testService.test1();
    }



}