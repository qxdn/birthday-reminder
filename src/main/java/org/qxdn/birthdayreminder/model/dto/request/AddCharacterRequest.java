package org.qxdn.birthdayreminder.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddCharacterRequest {

    @NotBlank(message = "名字不能为空")
    protected String name;

    @NotBlank(message = "原名不能为空")
    protected String originName;

    @NotBlank(message = "性别不能为空")
    protected String gender;

    @NotNull(message = "其他名不能为空")
    protected List<String> otherName = new ArrayList<>();


    /**
     * bangumi的id
     */
    protected Long bangumiId;

    protected String birthday;

    @NotNull(message = "图片不能为空")
    @Size(min = 1, message = "图片不能为空")
    protected List<String> images = new ArrayList<>();

    protected String comment;
}
