package controller

import "github.com/gin-gonic/gin"

type UserController struct {
}

type LoginRequest struct {
	Username string `json:"username" binding:"required"`
	Password string `json:"password" binding:"required"`
}

// 登录请求
func (u *UserController) Login(ctx *gin.Context) {
	request := LoginRequest{}
	// TODO:
	if err := ctx.ShouldBindJSON(&request); err != nil {
		ctx.JSON(400, gin.H{"error": err.Error()})
		return
	}
}

func (u *UserController) Register(ctx *gin.Context) {
}
