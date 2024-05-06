package org.qxdn.birthdayreminder.controller;

import org.qxdn.birthdayreminder.facade.api.CharacterFacade;
import org.qxdn.birthdayreminder.model.dto.request.AddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.QueryCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.UpdateCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;
import org.qxdn.birthdayreminder.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@RequestMapping("/api/character")
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
        return characterFacade.searchCharacterWithBirthday(date);
    }

    @GetMapping("/query")
    public BaseResponse<List<CharacterVO>> queryCharacterList(@ModelAttribute QueryCharacterRequest request) {
        return characterFacade.queryCharacterList(request);
    }


    /**
     * 添加角色
     * @param request 请求参数
     * @return 返回结果
     */
    @PostMapping("/add")
    public BaseResponse<CharacterVO> addCharacter(@RequestBody AddCharacterRequest request) {
        return characterFacade.addCharacter(request);
    }

    /**
     * 更新角色信息
     * @param request 请求参数
     * @return 返回结果
     */
    @PostMapping("/update")
    public BaseResponse<CharacterVO> updateCharacter(@RequestBody UpdateCharacterRequest request) {
        return characterFacade.updateCharacter(request);
    }

    @GetMapping("/{id}")
    public BaseResponse<CharacterVO> queryById(@PathVariable Long id) {
        return characterFacade.queryById(id);
    }
}
