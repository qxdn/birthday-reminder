package org.qxdn.birthdayreminder.facade.api;

import org.qxdn.birthdayreminder.model.dto.request.AddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;

import java.util.Date;
import java.util.List;

public interface CharacterFacade {

    List<CharacterVO> searchCharacterWithBirthday(Date birthday);

    CharacterVO addCharacter(AddCharacterRequest request);
}
