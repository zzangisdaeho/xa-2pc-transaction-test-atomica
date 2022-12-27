package co.coinvestor.xa2pctransactiontest.service.nonXA;

import co.coinvestor.xa2pctransactiontest.firstDB.entity.TestEntity1;
import co.coinvestor.xa2pctransactiontest.firstDB.repository.TestEntity1Repository;
import co.coinvestor.xa2pctransactiontest.secondDB.entity.TestEntity2;
import co.coinvestor.xa2pctransactiontest.secondDB.repository.TestEntity2Repository;
import co.coinvestor.xa2pctransactiontest.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Profile("no")
public class TestServiceNonXA implements TestService {

    private final TestEntity1Repository testEntity1Repository;
    private final TestEntity2Repository testEntity2Repository;

    @Transactional("firstDBTransactionManager")
    public void test1(){
        //entity1 관련 트랜잭션이 작동중으로 트랜잭션이 끝날때까지 데이터 반영 안됨.
        TestEntity1 entity1 = testEntity1Repository.save(TestEntity1.builder().name("na1").age(1).build());
        testEntity1Repository.updateAge(1);

        //nonXA모드에서는 entity1에 관련된 트랜잭션만 있기 때문에 entity2는 관련 트랜잭션이 없어서 쿼리가 바로 반영(전체 롤백 불가능)
        TestEntity2 entity2 = testEntity2Repository.save(TestEntity2.builder().name2("na2").age2(2).build());
        TestEntity2 entity2_1 = testEntity2Repository.save(TestEntity2.builder().name2("na2").age2(3).build());
        TestEntity2 entity2_2 = testEntity2Repository.save(TestEntity2.builder().name2("na2na2na2na2na2na2na2na2na2na2").age2(4).build());
        testEntity2Repository.updateAge(3);
    }
}
