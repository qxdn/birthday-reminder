package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuerySubscriberRequest extends PageRequest {

    /**
     * id
     */
    private Long id;

    /**
     * email
     */
    private String email;

    /**
     * active
     */
    private Boolean active;
}
