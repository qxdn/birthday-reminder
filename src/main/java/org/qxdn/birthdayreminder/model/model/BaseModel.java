package org.qxdn.birthdayreminder.model.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {

    /**
     * id
     */
    protected Long id;

    protected Date gmtCreate;

    protected Date gmtUpdate;
}
