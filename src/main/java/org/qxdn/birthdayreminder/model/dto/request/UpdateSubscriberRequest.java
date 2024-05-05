package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSubscriberRequest extends AddSubscriberRequest{

    private Long id;

    private boolean active;


}
