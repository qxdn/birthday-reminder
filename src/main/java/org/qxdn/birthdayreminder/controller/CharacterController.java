package org.qxdn.birthdayreminder.controller;

import org.qxdn.birthdayreminder.facade.api.CharacterFacade;
import org.qxdn.birthdayreminder.model.dto.request.AddCharacterRequest;
import org.qxdn.birthdayreminder.model.dto.request.BatchAddCharacterRequest;
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
    public BaseResponse<List<CharacterVO>> searchCharacterWithBirthday(@RequestParam(name = "year",required = false) Integer year,@RequestParam(name = "month",required = false) Integer month,
                                                                       @RequestParam(name = "day",required = false) Integer day,@RequestParam(name = "neeYear",required = false,defaultValue = "false") Boolean needYear) {
        if (Objects.isNull(year) && Objects.isNull(month) && Objects.isNull(day)){
           Date now = new Date();
           if (needYear) {
               year = DateUtils.getYear(now);
           }
           month = DateUtils.getMonth(now);
           day = DateUtils.getDay(now);
        }
        return characterFacade.searchCharacterWithBirthday(year, month, day);
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

    @PostMapping("/batchAdd")
    public BaseResponse<List<CharacterVO>> batchAddCharacter(@RequestBody BatchAddCharacterRequest requests) {
        return characterFacade.batchAddCharacter(requests);
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
