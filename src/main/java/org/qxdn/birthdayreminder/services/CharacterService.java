package org.qxdn.birthdayreminder.services;

import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;
import org.qxdn.birthdayreminder.model.entity.CharacterContent;
import org.qxdn.birthdayreminder.model.entity.CharacterDO;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.repository.CharacterRepository;
import org.qxdn.birthdayreminder.utils.DateUtils;
import org.qxdn.birthdayreminder.utils.JSONUtils;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.qxdn.birthdayreminder.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    /** 搜寻生日
     * @param date
     * @return
     */
    public List<Character> searchCharactersBirthDay(Date date) {
        List<CharacterDO> characterDOS =  characterRepository.findCharactersWithBirthday(DateUtils.getMonth(date),DateUtils.getDay(date));
        return StreamUtils.map(characterDOS, this::convert2Model);
    }

    /**
     * 添加角色
     * @param character 角色
     * @return 修改后角色
     */
    public Character addCharacter(Character character) {
       CharacterDO characterDO = characterRepository.save(convert2DO(character));
       return convert2Model(characterDO);
    }


    private Character convert2Model(CharacterDO characterDO) {
        Character character = new Character();
        BeanUtils.copyProperties(characterDO, character);
        character.setOtherName(StringUtils.split(characterDO.getOtherName(), BirthdayConstants.CHARACTER_OTHER_NAME_SPLIT));
        CharacterContent content = JSONUtils.parseObject(characterDO.getContent(), CharacterContent.class);
        character.setContent(content);
        return character;
    }

    private CharacterDO convert2DO(Character character) {
        CharacterDO characterDO = new CharacterDO();
        BeanUtils.copyProperties(character, characterDO);
        characterDO.setOtherName(StringUtils.join(character.getOtherName(), BirthdayConstants.CHARACTER_OTHER_NAME_SPLIT));
        characterDO.setContent(JSONUtils.toJSONString(character.getContent()));
        return characterDO;
    }
}
