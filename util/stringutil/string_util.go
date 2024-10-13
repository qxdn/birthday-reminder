package stringutil

import (
	"strings"

	"github.com/qxdn/birthday/constant"
)

// 分割字符串
func Split(str string, separator string) []string {
	return strings.Split(str, separator)
}

func SplitDefault(str string) []string {
	return Split(str, constant.SPLITER)
}

func Join(strs []string, separator string) string {
	return strings.Join(strs, separator)
}

func JoinDefault(strs []string) string {
	return Join(strs, constant.SPLITER)
}
