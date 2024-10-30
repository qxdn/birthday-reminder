package facade

import (
	"github.com/qxdn/birthday/dto/requests"
	"github.com/qxdn/birthday/dto/responses"
)

type UserFacade struct {
}

func (u *UserFacade) Login(request *requests.LoginRequest) *responses.LoginVO {
	return &responses.LoginVO{}
}
