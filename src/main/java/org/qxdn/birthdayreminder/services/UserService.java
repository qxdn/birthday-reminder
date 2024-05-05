package org.qxdn.birthdayreminder.services;

import org.qxdn.birthdayreminder.context.PageTotalContextHolder;
import org.qxdn.birthdayreminder.model.dto.request.QueryUserRequest;
import org.qxdn.birthdayreminder.model.entity.UserDO;
import org.qxdn.birthdayreminder.model.model.User;
import org.qxdn.birthdayreminder.repository.UserRepository;
import org.qxdn.birthdayreminder.services.converter.UserConverter;
import org.qxdn.birthdayreminder.utils.PageUtils;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 用户
     */
    public User getUserById(Long id){
        UserDO userDO = userRepository.findById(id).orElse(null);
        return userConverter.convert2Model(userDO);
    }


    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return 用户
     */
    public User getUserByName(String name){
        UserDO userDO = userRepository.findByName(name);
        return userConverter.convert2Model(userDO);
    }

    /**
     * 保存用户
     * @param user 用户
     * @return 用户
     */
    public User save(User user){
        UserDO userDO = userConverter.convert2DO(user);
        userDO = userRepository.saveAndFlush(userDO);
        return userConverter.convert2Model(userDO);
    }

    public User update(User user){
        UserDO userDO = userConverter.convert2DO(user);
        userDO = userRepository.saveAndFlush(userDO);
        return userConverter.convert2Model(userDO);
    }

    /**
     * 查询用户
     * @param request 查询条件
     * @return 用户列表
     */
    public List<User> queryUsers(QueryUserRequest request){
        Pageable pageable = PageUtils.getPageable(request);
        Page<UserDO> page = userRepository.queryUsers(request.getId(),request.getName(),pageable);
        PageTotalContextHolder.remove();
        PageTotalContextHolder.set(page.getTotalElements());
        return StreamUtils.map(page.getContent(), userConverter::convert2Model);
    }
}
