package org.qxdn.birthdayreminder.repository;

import org.qxdn.birthdayreminder.model.entity.SubscriberDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubscriberRepository extends JpaRepository<SubscriberDO, Long>{

    List<SubscriberDO> findByActive(Boolean active);
}
