package org.qxdn.birthdayreminder.model.model;

import lombok.Data;
import org.qxdn.birthdayreminder.model.entity.CharacterContent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Character {

    private Integer id;

    private String name;

    private String originName;

    private List<String> otherName = new ArrayList<>();

    private Integer birthYear;

    private Integer birthMonth;

    private Integer birthday;

    private CharacterContent content = new CharacterContent();

    private String comment;

    private Date gmtCreate;

    private Date gmtUpdate;
}
