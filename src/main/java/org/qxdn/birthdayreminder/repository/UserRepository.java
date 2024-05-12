package org.qxdn.birthdayreminder.repository;

import org.qxdn.birthdayreminder.model.entity.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserDO, Long>{

    UserDO findByUsername(String username);

    UserDO findByEmail(String email);

    @Query("SELECT u FROM UserDO u " +
            "WHERE (:id IS NULL OR u.id = :id) " +
            "AND (:username IS NULL OR u.username like %:username%)" +
            "AND (:email IS NULL OR u.email like %:email%)" +
            "AND (:role IS NULL OR u.role = :role)")
    Page<UserDO> queryUsers(@Param("id") Long id,@Param("username") String username,@Param("email") String email,
                            @Param("role") String role,Pageable pageable);
}
