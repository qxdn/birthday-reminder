package model

type Character struct {
	BaseModel
	Name       string           // 名称
	OriginName string           // 原名
	OtherName  []string         // 其他名字
	BirthYear  int              // 出生年
	BirthMonth int              // 出生月
	BirthDay   int              // 出生日
	content    CharacterContent // 拓展内容
	Comment    string           // 备注
}
