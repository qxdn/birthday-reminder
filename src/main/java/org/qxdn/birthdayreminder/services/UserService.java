package org.qxdn.birthdayreminder.services;

import org.qxdn.birthdayreminder.model.entity.UserDO;
import org.qxdn.birthdayreminder.model.model.User;
import org.qxdn.birthdayreminder.repository.UserRepository;
import org.qxdn.birthdayreminder.services.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        userDO = userRepository.save(userDO);
        return userConverter.convert2Model(userDO);
    }
}
