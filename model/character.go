package model

import (
	"errors"
	"fmt"

	"github.com/qxdn/birthday/util/stringutil"
)

type CharacterOtherName []string

type Character struct {
	BaseModel
	Name       string             // 名称
	OriginName string             // 原名
	OtherName  CharacterOtherName `gorm:"type:json"` // 其他名字
	BirthYear  int                // 出生年
	BirthMonth int                // 出生月
	BirthDay   int                // 出生日
	Content    CharacterContent   // 拓展内容
	Comment    string             // 备注
}

func (name *CharacterOtherName) Scan(value interface{}) error {
	bytes, ok := value.([]byte)
	if !ok {
		return errors.New(fmt.Sprint("解码角色名失败:", value))
	}
	*name = stringutil.SplitDefault(string(bytes))
	return nil

}

func (name CharacterOtherName) Value() (interface{}, error) {
	otherName := stringutil.JoinDefault(name)
	return otherName, nil
}
