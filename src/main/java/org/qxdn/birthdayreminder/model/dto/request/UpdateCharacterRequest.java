package org.qxdn.birthdayreminder.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateCharacterRequest  {

    @NotNull(message = "角色id不能为空")
    private Long id;

    protected String name;

    protected String originName;

    protected String gender;

    protected List<String> otherName = new ArrayList<>();


    protected String birthday;

    protected List<String> images = new ArrayList<>();

    protected String comment;
}
