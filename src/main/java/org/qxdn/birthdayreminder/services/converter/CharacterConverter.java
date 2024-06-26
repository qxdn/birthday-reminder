package org.qxdn.birthdayreminder.services.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;
import org.qxdn.birthdayreminder.model.dto.request.AddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;
import org.qxdn.birthdayreminder.model.entity.CharacterDO;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.model.model.content.CharacterContent;
import org.qxdn.birthdayreminder.utils.JSONUtils;
import org.qxdn.birthdayreminder.utils.StringUtils;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface CharacterConverter {

    @Mapping(target = "content",expression = "java(json2map(characterDO.getContent()))")
    @Mapping(target = "otherName", expression = "java(otherName2List(characterDO.getOtherName()))")
    Character convert2Model(CharacterDO characterDO);

    @Mapping(target = "content", expression = "java(map2json(character.getContent()))")
    @Mapping(target = "otherName", expression = "java(list2otherName(character.getOtherName()))")
    CharacterDO convert2DO(Character character);

    @Mapping(target = "birthday", expression = "java(birthdayConverter(character))")
    @Mapping(target = ".", source = "content")
    CharacterVO convert2VO(Character character);


    @Mapping(target = "content",source = ".")
    @Mapping(target = "birthDay", source = "birthday", qualifiedByName = "birthday2day")
    @Mapping(target = "birthMonth", source = "birthday", qualifiedByName = "birthday2month")
    @Mapping(target = "birthYear", source = "birthday", qualifiedByName = "birthday2year")
    Character AddCharacterRequest2Model(AddCharacterRequest request);

    @Mapping(target = "content",source = ".")
    @Mapping(target = "birthDay", source = "birthday", qualifiedByName = "birthday2day")
    @Mapping(target = "birthMonth", source = "birthday", qualifiedByName = "birthday2month")
    @Mapping(target = "birthYear", source = "birthday", qualifiedByName = "birthday2year")
    Character updateCharacterRequest2Model(UpdateCharacterRequest request);

    default List<String> otherName2List(String otherName) {
        return StringUtils.split(otherName, BirthdayConstants.CHARACTER_OTHER_NAME_SPLIT);
    }

    default String list2otherName(List<String> otherName) {
        return StringUtils.join(otherName, BirthdayConstants.CHARACTER_OTHER_NAME_SPLIT);
    }

    default CharacterContent json2map(String content) {
        return JSONUtils.parseObject(content, CharacterContent.class);
    }

    default String map2json(CharacterContent content) {
        return JSONUtils.toJSONString(content);
    }

    default String birthdayConverter(Character character) {
        // 生日处理
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(character.getBirthYear())) {
            sb.append(character.getBirthYear()).append("-");
        }
        sb.append(StringUtils.leftPad(String.valueOf(character.getBirthMonth()), 2, '0')).append("-")
                .append(StringUtils.leftPad(String.valueOf(character.getBirthDay()), 2, '0'));
        return sb.toString();
    }

    @Named("birthday2day")
    default Integer birthday2day(String birthday) {
        String[] split = birthday.split("-");
        if (split.length == 2) {
            return Integer.parseInt(birthday.split("-")[1]);
        } else {
            return Integer.parseInt(birthday.split("-")[2]);
        }
    }

    @Named("birthday2month")
    default Integer birthday2month(String birthday) {
        String[] split = birthday.split("-");
        if (split.length == 2) {
            return Integer.parseInt(birthday.split("-")[0]);
        } else {
            return Integer.parseInt(birthday.split("-")[1]);
        }
    }

    @Named("birthday2year")
    default Integer birthday2year(String birthday) {
        String[] split = birthday.split("-");
        if (split.length == 2) {
            return null;
        } else {
            return Integer.parseInt(birthday.split("-")[0]);
        }
    }
}
