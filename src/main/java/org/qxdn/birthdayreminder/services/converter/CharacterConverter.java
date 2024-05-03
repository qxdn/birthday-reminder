package org.qxdn.birthdayreminder.services.converter;

import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;
import org.qxdn.birthdayreminder.model.entity.CharacterDO;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.model.model.content.CharacterContent;
import org.qxdn.birthdayreminder.utils.JSONUtils;
import org.qxdn.birthdayreminder.utils.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class CharacterConverter {
    public static Character convert2Model(CharacterDO characterDO) {
        if (characterDO == null) {
            return null;
        }
        Character character = new Character();
        BeanUtils.copyProperties(characterDO, character);
        character.setOtherName(StringUtils.split(characterDO.getOtherName(), BirthdayConstants.CHARACTER_OTHER_NAME_SPLIT));
        CharacterContent content = JSONUtils.parseObject(characterDO.getContent(), CharacterContent.class);
        character.setContent(content);
        return character;
    }

    public static CharacterDO convert2DO(Character character) {
        CharacterDO characterDO = new CharacterDO();
        BeanUtils.copyProperties(character, characterDO);
        characterDO.setOtherName(StringUtils.join(character.getOtherName(), BirthdayConstants.CHARACTER_OTHER_NAME_SPLIT));
        characterDO.setContent(JSONUtils.toJSONString(character.getContent()));
        return characterDO;
    }


    public static CharacterVO convert2VO(Character character) {
        CharacterVO characterVO = new CharacterVO();
        BeanUtils.copyProperties(character, characterVO);
        BeanUtils.copyProperties(character.getContent(), characterVO);
        // 生日处理
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(character.getBirthYear())) {
            sb.append(character.getBirthYear()).append("-");
        }
        sb.append(character.getBirthMonth()).append("-").append(character.getBirthday());
        characterVO.setBirthday(sb.toString());
        return characterVO;
    }
}
