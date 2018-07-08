package cn.mrbcy.sound.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Yang on 2018/7/8.
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.userName = :userName")
    User getUserByName(@Param("userName")String userName);
}
