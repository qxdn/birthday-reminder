package model

import "time"

// BaseModel 基础模型
type BaseModel struct {
	Id        int       `gorm:"primaryKey"`                       // 主键
	GmtCreate time.Time `gorm:"column:gmt_create;autoCreateTime"` // 创建时间
	GmtUpdate time.Time `gorm:"column:gmt_update;autoUpdateTime"` // 更新时间
}
