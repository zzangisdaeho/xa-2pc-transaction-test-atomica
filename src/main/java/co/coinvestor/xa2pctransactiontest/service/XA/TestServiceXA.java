package co.coinvestor.xa2pctransactiontest.service.XA;

import co.coinvestor.xa2pctransactiontest.firstDB.entity.TestEntity1;
import co.coinvestor.xa2pctransactiontest.firstDB.repository.TestEntity1Repository;
import co.coinvestor.xa2pctransactiontest.secondDB.entity.TestEntity2;
import co.coinvestor.xa2pctransactiontest.secondDB.repository.TestEntity2Repository;
import co.coinvestor.xa2pctransactiontest.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("at")
@Service
@RequiredArgsConstructor
public class TestServiceXA implements TestService {

    private final TestEntity1Repository testEntity1Repository;
    private final TestEntity2Repository testEntity2Repository;


    @Transactional
    public void test1(){
        TestEntity1 entity1 = testEntity1Repository.save(TestEntity1.builder().name("na1").age(1).build());
        testEntity1Repository.updateAge(1);

        //XA모드에서는 당연히 전체 데이터 커밋 안됨
        TestEntity2 entity2 = testEntity2Repository.save(TestEntity2.builder().name2("na2").age2(2).build());
        TestEntity2 entity2_1 = testEntity2Repository.save(TestEntity2.builder().name2("na2").age2(3).build());
        TestEntity2 entity2_2 = testEntity2Repository.save(TestEntity2.builder().name2("na2na2na2na2na2na2na2na2na2na2").age2(4).build());
        testEntity2Repository.updateAge(3);
    }
}
