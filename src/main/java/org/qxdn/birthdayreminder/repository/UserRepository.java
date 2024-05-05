package org.qxdn.birthdayreminder.repository;

import org.qxdn.birthdayreminder.model.entity.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserDO, Long>{

    UserDO findByName(String name);


    @Query("SELECT u FROM UserDO u " +
            "WHERE (:id IS NULL OR u.id = :id) " +
            "AND (:name IS NULL OR u.name like %:name%)")
    Page<UserDO> queryUsers(@Param("id") Long id,@Param("name") String name, Pageable pageable);
}
