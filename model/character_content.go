package model

import (
	"database/sql/driver"
	"encoding/json"
	"errors"
	"fmt"
)

type CharacterContent struct {
	Images []string // 图片
}

// 解码JSON数据
func (content *CharacterContent) Scan(value interface{}) error {
	bytes, ok := value.([]byte)
	if !ok {
		return errors.New(fmt.Sprint("解析角色Content失败:", value))
	}
	err := json.Unmarshal(bytes, content)
	return err
}

// 编码JSON数据
func (content CharacterContent) Value() (driver.Value, error) {
	return json.Marshal(content)
}
