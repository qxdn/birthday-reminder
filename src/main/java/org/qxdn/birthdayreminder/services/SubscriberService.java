package org.qxdn.birthdayreminder.services;

import jakarta.annotation.Nullable;
import org.qxdn.birthdayreminder.model.dto.request.QuerySubscriberRequest;
import org.qxdn.birthdayreminder.model.entity.SubscriberDO;
import org.qxdn.birthdayreminder.model.model.Subscriber;
import org.qxdn.birthdayreminder.repository.SubscriberRepository;
import org.qxdn.birthdayreminder.services.converter.SubscriberConverter;
import org.qxdn.birthdayreminder.utils.PageUtils;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Subscriber queryById(Long id) {
        SubscriberDO subscriberDO =  subscriberRepository.findById(id).orElse(null);
        return subscriberConverter.convert2Model(subscriberDO);
    }
    @Transactional
    public Subscriber save(Subscriber subscriber) {
        SubscriberDO subscriberDO = subscriberConverter.convert2DO(subscriber);
        subscriberDO = subscriberRepository.saveAndFlush(subscriberDO);
        return subscriberConverter.convert2Model(subscriberDO);
    }

    @Transactional
    public Subscriber update(Subscriber subscriber) {
        SubscriberDO subscriberDO = subscriberConverter.convert2DO(subscriber);
        subscriberDO = subscriberRepository.saveAndFlush(subscriberDO);
        return subscriberConverter.convert2Model(subscriberDO);
    }

    public List<Subscriber> querySubscribers(QuerySubscriberRequest request) {
        Pageable pageable = PageUtils.getPageable(request);
        Page<SubscriberDO> subscriberDOS = subscriberRepository.querySubscribers(request.getId(), request.getEmail(), request.getActive(),pageable);
        return StreamUtils.map(subscriberDOS.getContent(), subscriberConverter::convert2Model);
    }
}
