package org.qxdn.birthdayreminder.model.model.content;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色内容
 */
@Data
public class CharacterContent {

    /**
     * 照片
     */
    private List<String> images = new ArrayList<>();
}
