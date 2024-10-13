package model

type Subscriber struct {
	BaseModel
	Email  string // 邮箱
	Active bool   // 是否激活
}
