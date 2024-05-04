package org.qxdn.birthdayreminder.services.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.qxdn.birthdayreminder.model.dto.request.RegisterRequest;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserVO;
import org.qxdn.birthdayreminder.model.entity.UserDO;
import org.qxdn.birthdayreminder.model.model.User;
import org.qxdn.birthdayreminder.utils.PasswordUtils;

@Mapper(componentModel = "spring")
public interface UserConverter {

    User convert2Model(UserDO userDO);

    UserVO convert2VO(User user);

    UserDO convert2DO(User user);


    @Mapping(target = "password", source = "password", qualifiedByName = "passwordEncoder")
    User registerRequest2Model(RegisterRequest request);

    @Named("passwordEncoder")
    default String passwordEncoder(String password) {
        return PasswordUtils.encode(password);
    }
}
