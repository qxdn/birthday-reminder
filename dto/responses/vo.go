package responses

import "time"

type BaseVO struct{}

type BaseModelVO struct {
	BaseVO
	Id        int       `json:"id"`
	GmtCreate time.Time `json:"gmtCreate"`
	GmtUpdate time.Time `json:"gmtUpdate"`
}

/**
 * @Description: 用户VO
 */
type UserVO struct {
	BaseModelVO
	Username string `json:"username"`
	Email    string `json:"email"`
	Avatar   string `json:"avatar"`
	Role     string `json:"role"`
}

/**
 * @Description: 登录VO
 */
type LoginVO struct {
	BaseVO
	User  UserVO `json:"user"`
	Token string `json:"token"`
}

/**
 * @Description: 角色VO
 */
type CharacterVO struct {
	BaseModelVO
	Name       string   `json:"name"`
	OriginName string   `json:"originName"`
	Gender     string   `json:"gender"`
	OtherName  []string `json:"otherName"`
	Birthday   string   `json:"birthday"`
	Images     []string `json:"images"`
	Comment    string   `json:"comment"`
}

/**
 * @Description: 订阅者VO
 */
type SubscriberVO struct {
	BaseModelVO
	Email  string `json:"email"`
	Active bool   `json:"active"`
}
