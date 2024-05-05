package org.qxdn.birthdayreminder.services;

import jakarta.annotation.Nullable;
import org.qxdn.birthdayreminder.model.entity.SubscriberDO;
import org.qxdn.birthdayreminder.model.model.Subscriber;
import org.qxdn.birthdayreminder.repository.SubscriberRepository;
import org.qxdn.birthdayreminder.services.converter.SubscriberConverter;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberConverter subscriberConverter;

    @Autowired
    private SubscriberRepository subscriberRepository;


    public List<Subscriber> querySubscribers(@Nullable Boolean active) {
        List<SubscriberDO> subscriberDOS;
        if (Objects.isNull(active)) {
           subscriberDOS = subscriberRepository.findAll();
        } else {
            subscriberDOS = subscriberRepository.findByActive(active);
        }
        return StreamUtils.map(subscriberDOS, subscriberConverter::convert2Model);
    }
}
