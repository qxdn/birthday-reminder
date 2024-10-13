package model

type User struct {
	BaseModel
	Username string // 用户名
	Email    string // 邮箱
	Avatar   string // 头像
	Password string // 密码
	Role     string // 角色
}
