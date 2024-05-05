package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;

@Data
public class PageRequest {
    protected Integer current = 1;

    protected Integer pageSize = 20;


    protected String direction = "ASC";

    protected String orderBy = "id";

    public Integer compute() {
        return current-1;
    }


}
