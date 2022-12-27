package co.coinvestor.xa2pctransactiontest.secondDB.repository;

import co.coinvestor.xa2pctransactiontest.secondDB.entity.TestEntity2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
//@ActiveProfiles("no")
@ActiveProfiles("at")
class TestEntity2RepositoryTest {

    @Autowired
    TestEntity2Repository testEntity2Repository;

    @Test
    public void save(){
        testEntity2Repository.save(TestEntity2.builder().age2(20).name2("na2").build());
    }
}