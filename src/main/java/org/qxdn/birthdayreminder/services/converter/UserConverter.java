package org.qxdn.birthdayreminder.services.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.qxdn.birthdayreminder.model.dto.request.RegisterRequest;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserVO;
import org.qxdn.birthdayreminder.model.entity.UserDO;
import org.qxdn.birthdayreminder.model.enums.UserRoleEnum;
import org.qxdn.birthdayreminder.model.model.User;
import org.qxdn.birthdayreminder.utils.PasswordUtils;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mapping(target = "role", source = "role", qualifiedByName = "roleDecoder")
    User convert2Model(UserDO userDO);

    @Mapping(target = "role", source = "role", qualifiedByName = "roleEncoder")
    UserVO convert2VO(User user);

    @Mapping(target = "role", source = "role", qualifiedByName = "roleEncoder")
    UserDO convert2DO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gmtUpdate", ignore = true)
    @Mapping(target = "gmtCreate", ignore = true)
    @Mapping(target = "role", source = "role", qualifiedByName = "roleDecoder")
    @Mapping(target = "password", source = "password", qualifiedByName = "passwordEncoder")
    User registerRequest2Model(RegisterRequest request);

    @Named("passwordEncoder")
    default String passwordEncoder(String password) {
        return PasswordUtils.encode(password);
    }

    @Named("roleDecoder")
    default UserRoleEnum convertRole2Enum(String role) {
        return UserRoleEnum.getByRole(role);
    }

    @Named("roleEncoder")
    default String convertEnum2Role(UserRoleEnum role) {
        return role.getRole();
    }

}
