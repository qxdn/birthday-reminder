package org.qxdn.birthdayreminder.services.converter;

import org.mapstruct.Mapper;
import org.qxdn.birthdayreminder.model.entity.SubscriberDO;
import org.qxdn.birthdayreminder.model.model.Subscriber;

@Mapper(componentModel = "spring")
public interface SubscriberConverter {

    Subscriber convert2Model(SubscriberDO subscriberDO);

    SubscriberDO convert2DO(Subscriber subscriber);
}
