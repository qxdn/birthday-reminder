package controller

import (
	"github.com/gin-gonic/gin"
	"github.com/qxdn/birthday/dto/requests"
	"github.com/qxdn/birthday/util/validateutil"
)

type UserController struct {
}

// 登录请求
func (u *UserController) Login(ctx *gin.Context) {
	request := requests.LoginRequest{}
	// TODO:
	if err := ctx.ShouldBindJSON(&request); err != nil {
		ctx.JSON(400, gin.H{"error": err.Error()})
		return
	}
	err := validateutil.ValidateStruct(request)
	if err != nil {
		ctx.JSON(400, gin.H{"error": err.Error()})
		return
	}
}

func (u *UserController) Register(ctx *gin.Context) {
}
