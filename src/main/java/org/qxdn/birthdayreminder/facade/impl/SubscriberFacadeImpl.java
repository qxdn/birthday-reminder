package org.qxdn.birthdayreminder.facade.impl;

import org.qxdn.birthdayreminder.context.PageTotalContextHolder;
import org.qxdn.birthdayreminder.facade.api.SubscriberFacade;
import org.qxdn.birthdayreminder.model.dto.request.AddSubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.request.QuerySubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateSubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.SubscriberVO;
import org.qxdn.birthdayreminder.model.model.Subscriber;
import org.qxdn.birthdayreminder.services.SubscriberService;
import org.qxdn.birthdayreminder.services.converter.SubscriberConverter;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubscriberFacadeImpl implements SubscriberFacade {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SubscriberConverter subscriberConverter;

    @Override
    public BaseResponse<SubscriberVO> addSubscriber(AddSubscriberRequest request) {
        Subscriber subscriber = subscriberConverter.AddSubscriberRequest2Model(request);
        if (Objects.isNull(subscriber.getActive())) {
            subscriber.setActive(false);
        }
        subscriber = subscriberService.save(subscriber);
        return BaseResponse.success(subscriberConverter.convert2VO(subscriber));
    }

    @Override
    public BaseResponse<SubscriberVO> updateSubscriber(UpdateSubscriberRequest request) {
        Subscriber subscriber = subscriberConverter.updateSubscriberRequest2Model(request);
        subscriber = subscriberService.update(subscriber);
        return BaseResponse.success(subscriberConverter.convert2VO(subscriber));
    }

    @Override
    public BaseResponse<List<SubscriberVO>> querySubscribers(QuerySubscriberRequest request) {
        List<Subscriber> subscribers = subscriberService.querySubscribers(request);
        List<SubscriberVO> subscriberVOS = StreamUtils.map(subscribers, subscriberConverter::convert2VO);
        Long total = PageTotalContextHolder.get();
        PageTotalContextHolder.remove();
        return new  BaseResponse<>(subscriberVOS, total);
    }

    @Override
    public BaseResponse<SubscriberVO> queryById(Long id) {
        Subscriber subscriber = subscriberService.queryById(id);
        return BaseResponse.success(subscriberConverter.convert2VO(subscriber));
    }
}
