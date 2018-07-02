package cn.mrbcy.sound.domain;

import cn.mrbcy.sound.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yang on 2018/6/23.
 */
public interface TagJpaRepository extends JpaRepository<Tag, Long> {
    @Query(value="SELECT t FROM Tag t WHERE t.name like CONCAT('%',:name,'%')")
    public List<Tag> findTagLikeName(@Param("name")String name);
}
