package co.coinvestor.xa2pctransactiontest.service;

import co.coinvestor.xa2pctransactiontest.firstDB.entity.TestEntity1;
import co.coinvestor.xa2pctransactiontest.firstDB.repository.TestEntity1Repository;
import co.coinvestor.xa2pctransactiontest.secondDB.entity.TestEntity2;
import co.coinvestor.xa2pctransactiontest.secondDB.repository.TestEntity2Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("no")
class TestServiceTestNonXA {

    @Autowired
    private TestService testService;
    @Autowired
    private TestEntity1Repository testEntity1Repository;
    @Autowired
    private TestEntity2Repository testEntity2Repository;

    @Test
    public void test1(){
        try{
            testService.test1();
        }catch (RuntimeException ignored){
            ignored.printStackTrace();
        }
        List<TestEntity1> entity1List = testEntity1Repository.findAll();
        List<TestEntity2> entity2List = testEntity2Repository.findAll();
        System.out.println("entity2List size = " + entity2List.size());

        Assertions.assertThat(entity1List).hasSize(0);
        Assertions.assertThat(entity2List).hasSizeGreaterThan(0);

    }



}