package org.qxdn.birthdayreminder.charactertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.qxdn.birthdayreminder.facade.api.CharacterFacade;
import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.CharacterVO;
import org.qxdn.birthdayreminder.model.entity.CharacterDO;
import org.qxdn.birthdayreminder.model.model.content.CharacterContent;
import org.qxdn.birthdayreminder.repository.CharacterRepository;
import org.qxdn.birthdayreminder.utils.JSONUtils;
import org.qxdn.birthdayreminder.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
public class QueryByIdTest {

    @MockBean
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterFacade characterFacade;

    @BeforeEach
    public void mockData(){
        Mockito.when(characterRepository.findById(1L)).thenReturn(Optional.empty());
        CharacterDO characterDO = new CharacterDO();
        characterDO.setId(2L);
        characterDO.setGender("F");
        characterDO.setName("test");
        characterDO.setOriginName("test");
        characterDO.setOtherName(StringUtils.join(List.of("test2","test3"), BirthdayConstants.CHARACTER_OTHER_NAME_SPLIT));
        characterDO.setComment("无");
        characterDO.setBirthYear(2000);
        characterDO.setBirthMonth(1);
        characterDO.setBirthDay(1);
        CharacterContent content = new CharacterContent();
        content.setImages(new ArrayList<>());
        characterDO.setContent(JSONUtils.toJSONString(content));
        Mockito.when(characterRepository.findById(2L)).thenReturn(Optional.of(characterDO));
    }


    public static Stream<Arguments> provideTestData(){

        CharacterVO vo = new CharacterVO();
        vo.setId(2L);
        vo.setName("test");
        vo.setOriginName("test");
        vo.setGender("F");
        vo.setOtherName(List.of("test2","test3"));
        vo.setBirthday("2000-01-01");
        vo.setImages(new ArrayList<>());
        vo.setComment("无");


        return Stream.of(
                Arguments.of(1L,new BaseResponse<>()),
                Arguments.of(2L,new BaseResponse<>(vo))
        );
    }


    @DisplayName("测试根据id查询角色")
    @ParameterizedTest
    @MethodSource("provideTestData")
    public void test(Long id, BaseResponse<CharacterVO> result){
        BaseResponse<CharacterVO> response = characterFacade.queryById(id);
        System.out.println(response);
        assert response.equals(result);
    }

}
