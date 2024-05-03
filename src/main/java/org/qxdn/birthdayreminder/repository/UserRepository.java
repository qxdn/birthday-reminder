package org.qxdn.birthdayreminder.repository;

import org.qxdn.birthdayreminder.model.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO, Long>{

    UserDO findByName(String name);
}
