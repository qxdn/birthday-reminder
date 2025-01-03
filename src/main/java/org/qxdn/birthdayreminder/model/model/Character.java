package org.qxdn.birthdayreminder.model.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.qxdn.birthdayreminder.model.model.content.CharacterContent;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Character extends BaseModel {

    private String name;

    private String originName;

    private List<String> otherName = new ArrayList<>();

    /**
     * 角色性别 考虑到性别秀吉，还是直接中文
     */
    private String gender;

    private Integer birthYear;

    private Integer birthMonth;

    private Integer birthDay;

    /**
     * bangumi的id
     */
    private Long bangumiId;

    private CharacterContent content = new CharacterContent();

    private String comment;
}
