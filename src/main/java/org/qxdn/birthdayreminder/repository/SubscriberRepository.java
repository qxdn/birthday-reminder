package org.qxdn.birthdayreminder.repository;

import org.qxdn.birthdayreminder.model.entity.SubscriberDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SubscriberRepository extends JpaRepository<SubscriberDO, Long>{

    List<SubscriberDO> findByActive(Boolean active);

    @Query("SELECT s FROM subscriber s WHERE " +
            "(:id is null or s.id = :id) " +
            "and (:email is null or s.email like %:email%) " +
            "and (:active is null or s.active = :active)")
    Page<SubscriberDO> querySubscribers(@Param("id") Long id,@Param("email") String email,
                                       @Param("active") Boolean active, Pageable pageable);
}
