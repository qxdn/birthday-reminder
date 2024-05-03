package org.qxdn.birthdayreminder.facade.impl;

import org.qxdn.birthdayreminder.facade.api.CharacterFacade;
import org.qxdn.birthdayreminder.model.dto.request.AddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.services.CharacterService;
import org.qxdn.birthdayreminder.services.converter.CharacterConverter;
import org.qxdn.birthdayreminder.utils.CheckUtils;
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
    public BaseResponse<List<CharacterVO>> searchCharacterWithBirthday(Date birthday) {
        List<Character> characters = characterService.searchCharactersBirthDay(birthday);
        List<CharacterVO> vos = StreamUtils.map(characters, CharacterConverter::convert2VO);
        return new BaseResponse<>(vos);
    }

    @Override
    public BaseResponse<CharacterVO> addCharacter(AddCharacterRequest request) {
        //TODO: check request
        Character character = new Character();
        BeanUtils.copyProperties(request, character);
        CharacterVO vo = CharacterConverter.convert2VO(characterService.addCharacter(character));
        return new BaseResponse<>(vo);
    }

    @Override
    public BaseResponse<CharacterVO> updateCharacter(UpdateCharacterRequest request) {
        //TODO: check request
        CheckUtils.notNull(request.getId());
        Character character = new Character();
        BeanUtils.copyProperties(request, character);
        CharacterVO vo = CharacterConverter.convert2VO(characterService.updateCharacter(character));
        return new BaseResponse<>(vo);
    }
}
