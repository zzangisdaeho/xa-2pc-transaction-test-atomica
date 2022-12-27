package co.coinvestor.xa2pctransactiontest.secondDB.repository;

import co.coinvestor.xa2pctransactiontest.secondDB.entity.TestEntity2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TestEntity2Repository extends JpaRepository<TestEntity2, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update TestEntity2 t2 set t2.age2 = t2.age2+1 where t2.age2 = :age")
    int updateAge(int age);
}
