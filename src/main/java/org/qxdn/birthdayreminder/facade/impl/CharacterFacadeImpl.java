package org.qxdn.birthdayreminder.facade.impl;

import org.qxdn.birthdayreminder.facade.api.CharacterFacade;
import org.qxdn.birthdayreminder.model.dto.request.AddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.services.CharacterService;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CharacterFacadeImpl implements CharacterFacade {

    @Autowired
    private CharacterService characterService;

    @Override
    public List<CharacterVO> searchCharacterWithBirthday(Date birthday) {
        List<Character> characters = characterService.searchCharactersBirthDay(birthday);
        return StreamUtils.map(characters, this::convertCharacter2VO);
    }

    @Override
    public CharacterVO addCharacter(AddCharacterRequest request) {
        Character character = new Character();
        BeanUtils.copyProperties(request, character);
        return convertCharacter2VO(characterService.addCharacter(character));
    }

    private CharacterVO convertCharacter2VO(Character character) {
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
