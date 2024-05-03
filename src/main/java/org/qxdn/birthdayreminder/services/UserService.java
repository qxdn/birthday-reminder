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

    public User getUserById(Long id){
        UserDO userDO = userRepository.findById(id).orElse(null);
        return UserConverter.convert2Model(userDO);
    }


    public User getUserByName(String name){
        UserDO userDO = userRepository.findByName(name);
        return UserConverter.convert2Model(userDO);
    }

}
