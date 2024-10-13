package model

import "time"

// BaseModel 基础模型
type BaseModel struct {
	Id        int       // 主键
	GmtCreate time.Time // 创建时间
	GmtUpdate time.Time // 更新时间
}
