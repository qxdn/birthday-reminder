package org.qxdn.birthdayreminder.controller;

import org.qxdn.birthdayreminder.facade.api.CharacterFacade;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;
import org.qxdn.birthdayreminder.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@RequestMapping("/character")
@RestController
public class CharacterController {

    @Autowired
    private CharacterFacade characterFacade;

    @GetMapping("/birthday")
    public BaseResponse<List<CharacterVO>> searchCharacterWithBirthday(@RequestParam(name = "birthday",required = false) String birthday) {
        Date date = DateUtils.now();
        if (Objects.nonNull(birthday)){
            date = DateUtils.parseYMD(birthday);
        }
        List<CharacterVO> characterVOS = characterFacade.searchCharacterWithBirthday(date);
        return new BaseResponse<>(characterVOS);
    }
}
