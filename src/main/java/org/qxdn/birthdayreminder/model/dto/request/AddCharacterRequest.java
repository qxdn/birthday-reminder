package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddCharacterRequest {

    private String name;

    private String originName;

    private List<String> otherName = new ArrayList<>();

    private String birthday;

    private List<String> images = new ArrayList<>();

    private String comment;
}
