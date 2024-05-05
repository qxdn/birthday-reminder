package org.qxdn.birthdayreminder.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "characters",indexes = {
        @Index(name = "idx_name",columnList = "name"),
        @Index(name = "idx_origin_name",columnList = "origin_name"),
        @Index(name = "idx_birthday",columnList = "birth_month"),
        @Index(name = "idx_birthday",columnList = "birth_day"),
})
public class CharacterDO extends BaseDO {

    /**
     * 角色名称
     */
    @Column(name = "name",nullable = false)
    private String name;

    /**
     * 角色原名 主要为日文、英文名 翻译前的原名
     */
    @Column(name = "origin_name",nullable = false)
    private String originName;

    /**
     * 角色别名
     * @see org.qxdn.birthdayreminder.model.constants.BirthdayConstants CHARACTER_OTHER_NAME_SPLIT
     */
    @Column(name = "other_name")
    private String otherName = "";
    /**
     * 角色性别 考虑到性别秀吉，还是直接中文
     */
    private String gender;

    /**
     * 角色生日 日
     */
    @Column(name = "birth_day",nullable = false)
    private Integer birthDay;

    /**
     * 角色生日 月
     */
    @Column(name = "birth_month",nullable = false)
    private Integer birthMonth;

    /**
     * 角色生日 年
     */
    @Column(name = "birth_year")
    private Integer birthYear;
    /**
     * 大字段，保存内容
     */
    @Column(name = "content",columnDefinition = "LONGTEXT")
    private String content;

    /**
     * 备注
     */
    @Column(name = "`comment`")
    private String comment = "无";

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CharacterDO that = (CharacterDO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
