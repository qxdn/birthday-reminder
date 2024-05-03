package org.qxdn.birthdayreminder.repository;

import org.qxdn.birthdayreminder.model.entity.CharacterDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CharacterRepository extends JpaRepository<CharacterDO, Integer> {

    @Query("SELECT c FROM CharacterDO c WHERE c.birthMonth = :month AND c.birthDay = :day")
    List<CharacterDO> findCharactersWithBirthday(@Param("month") Integer month, @Param("day") Integer birthday);
}
