package co.coinvestor.xa2pctransactiontest.service;

import co.coinvestor.xa2pctransactiontest.firstDB.entity.TestEntity1;
import co.coinvestor.xa2pctransactiontest.firstDB.repository.TestEntity1Repository;
import co.coinvestor.xa2pctransactiontest.secondDB.entity.TestEntity2;
import co.coinvestor.xa2pctransactiontest.secondDB.repository.TestEntity2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestEntity1Repository testEntity1Repository;
    private final TestEntity2Repository testEntity2Repository;


    @Transactional
//    @Transactional("firstDBTransactionManager")
    public void test1(){
        testEntity1Repository.save(TestEntity1.builder().name("na1").age(1).build());
        testEntity2Repository.save(TestEntity2.builder().name2("na2").age2(2).build());
    }
}
