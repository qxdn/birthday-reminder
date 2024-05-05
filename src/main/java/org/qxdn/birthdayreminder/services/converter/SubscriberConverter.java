package org.qxdn.birthdayreminder.services.converter;

import org.mapstruct.Mapper;
import org.qxdn.birthdayreminder.model.dto.request.AddSubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateSubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.response.vo.SubscriberVO;
import org.qxdn.birthdayreminder.model.entity.SubscriberDO;
import org.qxdn.birthdayreminder.model.model.Subscriber;

@Mapper(componentModel = "spring")
public interface SubscriberConverter {

    Subscriber convert2Model(SubscriberDO subscriberDO);

    SubscriberDO convert2DO(Subscriber subscriber);

    SubscriberVO convert2VO(Subscriber subscriber);

    Subscriber AddSubscriberRequest2Model(AddSubscriberRequest request);

    Subscriber updateSubscriberRequest2Model(UpdateSubscriberRequest request);
}
