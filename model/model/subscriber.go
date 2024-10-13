package model

type Subscriber struct {
	BaseModel
	Email  string // 邮箱
	active bool   // 是否激活
}
