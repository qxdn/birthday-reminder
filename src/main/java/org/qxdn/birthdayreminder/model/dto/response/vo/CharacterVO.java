package org.qxdn.birthdayreminder.model.dto.response.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CharacterVO {
    private Long id;

    private String name;

    private String originName;

    private String gender;

    private List<String> otherName = new ArrayList<>();

    private String birthday;

    private List<String> images = new ArrayList<>();

    private String comment;

    private Date gmtCreate;

    private Date gmtUpdate;
}
