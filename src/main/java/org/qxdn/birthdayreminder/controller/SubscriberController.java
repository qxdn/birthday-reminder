package org.qxdn.birthdayreminder.controller;

import org.qxdn.birthdayreminder.facade.api.SubscriberFacade;
import org.qxdn.birthdayreminder.model.dto.request.AddSubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.request.QuerySubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateSubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.SubscriberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/subscriber")
@RestController
public class SubscriberController {

    @Autowired
    private SubscriberFacade subscriberFacade;

    @PostMapping("/add")
    public BaseResponse<SubscriberVO> addSubscriber(AddSubscriberRequest request) {
        return subscriberFacade.addSubscriber(request);
    }

    @PostMapping("/update")
    public BaseResponse<SubscriberVO> updateSubscriber(UpdateSubscriberRequest request) {
        return subscriberFacade.updateSubscriber(request);
    }

    @GetMapping("/query")
    public BaseResponse<List<SubscriberVO>> querySubscribers(QuerySubscriberRequest request) {
        return subscriberFacade.querySubscribers(request);
    }

    @GetMapping("/{id}")
    public BaseResponse<SubscriberVO> queryById(@PathVariable Long id) {
        return subscriberFacade.queryById(id);
    }
}
