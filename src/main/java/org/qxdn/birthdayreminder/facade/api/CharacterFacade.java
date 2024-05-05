package org.qxdn.birthdayreminder.facade.api;

import org.qxdn.birthdayreminder.model.dto.request.AddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.QueryCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;

import java.util.Date;
import java.util.List;

public interface CharacterFacade {

    BaseResponse<List<CharacterVO>> searchCharacterWithBirthday(Date birthday);

    BaseResponse<List<CharacterVO>> queryCharacterList(QueryCharacterRequest request);

    BaseResponse<CharacterVO> addCharacter(AddCharacterRequest request);

    BaseResponse<CharacterVO> updateCharacter(UpdateCharacterRequest request);

    BaseResponse<CharacterVO> queryById(Long id);
}
