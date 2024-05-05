package org.qxdn.birthdayreminder.facade.api;

import org.qxdn.birthdayreminder.model.dto.request.AddSubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.request.QuerySubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateSubscriberRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.SubscriberVO;

import java.util.List;

public interface SubscriberFacade {

    /**
     * 添加订阅者
     * @param request 订阅者信息
     * @return 订阅者信息
     */
    BaseResponse<SubscriberVO> addSubscriber(AddSubscriberRequest request);

    /**
     * 更新订阅者
     * @param request 订阅者信息
     * @return 订阅者信息
     */
    BaseResponse<SubscriberVO> updateSubscriber(UpdateSubscriberRequest request);

    /**
     * 查询订阅者
     * @param request 查询条件
     * @return 订阅者列表
     */
    BaseResponse<List<SubscriberVO>> querySubscribers(QuerySubscriberRequest request);

    /**
     * 根据id查询订阅者
     * @param id 订阅者id
     * @return 订阅者信息
     */
    BaseResponse<SubscriberVO> queryById(Long id);
}
