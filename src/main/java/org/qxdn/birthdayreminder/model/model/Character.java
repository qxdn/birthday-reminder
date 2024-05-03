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

    private Integer birthYear;

    private Integer birthMonth;

    private Integer birthday;

    private CharacterContent content = new CharacterContent();

    private String comment;
}
