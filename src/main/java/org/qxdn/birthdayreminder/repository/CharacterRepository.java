package org.qxdn.birthdayreminder.repository;

import org.qxdn.birthdayreminder.model.entity.CharacterDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacterRepository extends JpaRepository<CharacterDO, Long> {

    @Query("SELECT c FROM CharacterDO c WHERE c.birthMonth = :month AND c.birthDay = :day")
    List<CharacterDO> findCharactersWithBirthday(@Param("month") Integer month, @Param("day") Integer birthday);

    @Query("SELECT c FROM CharacterDO c " +
            "WHERE (:id IS NULL OR c.id = :id) " +
            "AND (:name IS NULL OR c.name like %:name%) " +
            "AND (:originName IS NULL OR c.originName like %:originName%)" +
            "AND (:birthYear IS NULL OR c.birthYear = :birthYear)" +
            "AND (:birthMonth IS NULL OR c.birthMonth = :birthMonth)" +
            "AND (:birthDay IS NULL OR c.birthDay = :birthDay)")
    Page<CharacterDO> queryCharacters(@Param("id") Long id, @Param("name") String name,
                                      @Param("originName") String originName, @Param("birthYear") Integer birthYear,
                                      @Param("birthMonth") Integer birthMonth, @Param("birthDay") Integer birthDay,
                                      Pageable pageable);
}
