package org.qxdn.birthdayreminder.facade.impl;

import org.qxdn.birthdayreminder.context.PageTotalContextHolder;
import org.qxdn.birthdayreminder.facade.api.CharacterFacade;
import org.qxdn.birthdayreminder.model.dto.request.AddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.BatchAddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.QueryCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;
import org.qxdn.birthdayreminder.model.model.Character;
import org.qxdn.birthdayreminder.services.CharacterService;
import org.qxdn.birthdayreminder.services.converter.CharacterConverter;
import org.qxdn.birthdayreminder.utils.CheckUtils;
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CharacterFacadeImpl implements CharacterFacade {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CharacterConverter characterConverter;

    @Override
    public BaseResponse<List<CharacterVO>> searchCharacterWithBirthday(Date birthday) {
        List<Character> characters = characterService.searchCharactersBirthDay(birthday);
        List<CharacterVO> vos = StreamUtils.map(characters, characterConverter::convert2VO);
        return new BaseResponse<>(vos);
    }

    @Override
    public BaseResponse<List<CharacterVO>> queryCharacterList(QueryCharacterRequest request) {
        List<Character> characters = characterService.query(request);
        Long total = PageTotalContextHolder.get();
        List<CharacterVO> vos = StreamUtils.map(characters, characterConverter::convert2VO);
        PageTotalContextHolder.remove();
        return new BaseResponse<>(vos, total);
    }


    @Transactional
    @Override
    public BaseResponse<CharacterVO> addCharacter(AddCharacterRequest request) {
        //TODO: check request
        Character character = characterConverter.AddCharacterRequest2Model(request);
        character = characterService.addCharacter(character);
        CharacterVO vo = characterConverter.convert2VO(character);
        return new BaseResponse<>(vo);
    }

    @Override
    @Transactional
    public BaseResponse<List<CharacterVO>> batchAddCharacter(BatchAddCharacterRequest requests) {
        List<Character> characters = StreamUtils.map(requests.getBatchData(), characterConverter::AddCharacterRequest2Model);
        characters = characterService.batchAddCharacter(characters);
        List<CharacterVO> vos = StreamUtils.map(characters, characterConverter::convert2VO);
        return new BaseResponse<>(vos);
    }

    @Transactional
    @Override
    public BaseResponse<CharacterVO> updateCharacter(UpdateCharacterRequest request) {
        //TODO: check request
        CheckUtils.notNull(request.getId());
        Character character = characterConverter.updateCharacterRequest2Model(request);
        character = characterService.updateCharacter(character);
        CharacterVO vo = characterConverter.convert2VO(character);
        return new BaseResponse<>(vo);
    }

    @Override
    public BaseResponse<CharacterVO> queryById(Long id) {
        Character character = characterService.queryById(id);
        CharacterVO vo = characterConverter.convert2VO(character);
        return new BaseResponse<>(vo);
    }
}
