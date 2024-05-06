package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddCharacterRequest {

    protected String name;

    protected String originName;

    protected String gender;

    protected List<String> otherName = new ArrayList<>();

    protected String birthday;

    protected List<String> images = new ArrayList<>();

    protected String comment;
}
