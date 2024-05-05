package org.qxdn.birthdayreminder.model.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Subscriber extends BaseModel {

    private String email;

    private Boolean active;
}
