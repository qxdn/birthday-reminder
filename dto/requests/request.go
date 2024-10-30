package requests

/**
 * @title LoginRequest
 * @description LoginRequest is a struct that represents the request body for the login endpoint.
 **/
type LoginRequest struct {
	Username string `json:"username" binding:"required" validate:"required,min=3,max=20"`
	Password string `json:"password" binding:"required" validate:"required,min=6,max=20"`
}
