package org.qxdn.birthdayreminder.services;


import org.qxdn.birthdayreminder.model.entity.CharacterDO;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.repository.CharacterRepository;
import org.qxdn.birthdayreminder.services.converter.CharacterConverter;
import org.qxdn.birthdayreminder.utils.DateUtils;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterConverter characterConverter;

    /** 搜寻生日
     * @param date
     * @return
     */
    public List<Character> searchCharactersBirthDay(Date date) {
        List<CharacterDO> characterDOS =  characterRepository.findCharactersWithBirthday(DateUtils.getMonth(date),DateUtils.getDay(date));
        return StreamUtils.map(characterDOS, characterConverter::convert2Model);
    }

    /**
     * 添加角色
     * @param character 角色
     * @return 修改后角色
     */
    public Character addCharacter(Character character) {
       CharacterDO characterDO = characterRepository.save(characterConverter.convert2DO(character));
       return characterConverter.convert2Model(characterDO);
    }

    /**
     * 更新角色
     * @param character 角色
     * @return 修改后角色
     */
    public Character updateCharacter(Character character) {
       CharacterDO characterDO = characterRepository.save(characterConverter.convert2DO(character));
       return characterConverter.convert2Model(characterDO);
    }

}
