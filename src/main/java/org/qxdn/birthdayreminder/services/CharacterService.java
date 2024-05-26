package org.qxdn.birthdayreminder.services;


import jakarta.annotation.Nullable;
import org.qxdn.birthdayreminder.context.PageTotalContextHolder;
import org.qxdn.birthdayreminder.model.dto.request.QueryCharacterRequest;
import org.qxdn.birthdayreminder.model.entity.CharacterDO;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.repository.CharacterRepository;
import org.qxdn.birthdayreminder.services.converter.CharacterConverter;
import org.qxdn.birthdayreminder.utils.DateUtils;
import org.qxdn.birthdayreminder.utils.PageUtils;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterConverter characterConverter;

    /** 搜寻生日
     * @param date 日期
     * @return 角色列表
     */
    public List<Character> searchCharactersBirthday(@Nullable Integer year, Integer month, Integer day) {
        List<CharacterDO> characterDOS =  characterRepository.findCharactersWithBirthday(year, month, day);
        return StreamUtils.map(characterDOS, characterConverter::convert2Model);
    }

    public Character queryById(Long id) {
        CharacterDO characterDO =  characterRepository.findById(id).orElse(null);
        return characterConverter.convert2Model(characterDO);
    }

    /**
     * 查询角色
     * @param request 查询条件
     * @return 角色列表
     */
    public List<Character> query(QueryCharacterRequest request){
        Pageable pageable = PageUtils.getPageable(request);
        Page<CharacterDO> page = characterRepository.queryCharacters(request.getId(),request.getName()
                ,request.getOriginName(), request.getBirthYear(),
                request.getBirthMonth(), request.getBirthDay(),
                pageable);
        PageTotalContextHolder.remove();
        PageTotalContextHolder.set(page.getTotalElements());
        return StreamUtils.map(page.getContent(), characterConverter::convert2Model);
    }

    /**
     * 添加角色
     * @param character 角色
     * @return 修改后角色
     */
    @Transactional
    public Character addCharacter(Character character) {
       CharacterDO characterDO = characterRepository.save(characterConverter.convert2DO(character));
       return characterConverter.convert2Model(characterDO);
    }

    @Transactional
    public List<Character> batchAddCharacter(List<Character> characters) {
        List<CharacterDO> characterDOS = StreamUtils.map(characters, characterConverter::convert2DO);
        characterRepository.saveAll(characterDOS);
        return StreamUtils.map(characterDOS, characterConverter::convert2Model);
    }

    /**
     * 更新角色
     * @param character 角色
     * @return 修改后角色
     */
    @Transactional
    public Character updateCharacter(Character character) {
       CharacterDO characterDO = characterRepository.save(characterConverter.convert2DO(character));
       return characterConverter.convert2Model(characterDO);
    }

}
