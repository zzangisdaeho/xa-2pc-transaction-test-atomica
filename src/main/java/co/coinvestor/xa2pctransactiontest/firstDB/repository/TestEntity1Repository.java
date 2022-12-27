package co.coinvestor.xa2pctransactiontest.firstDB.repository;

import co.coinvestor.xa2pctransactiontest.firstDB.entity.TestEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TestEntity1Repository extends JpaRepository<TestEntity1, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update TestEntity1 t1 set t1.age = t1.age+1 where t1.age = :age")
    int updateAge(int age);
}
