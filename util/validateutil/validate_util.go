package validateutil

import "github.com/go-playground/validator/v10"

var validate = validator.New(validator.WithRequiredStructEnabled())

/**
 * @Description: 验证结构体
 * @param s interface{}
 * @return error 错误
 */
func ValidateStruct(s interface{}) error {
	return validate.Struct(s)
}
