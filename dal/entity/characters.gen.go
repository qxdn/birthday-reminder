// Code generated by gorm.io/gen. DO NOT EDIT.
// Code generated by gorm.io/gen. DO NOT EDIT.
// Code generated by gorm.io/gen. DO NOT EDIT.

package entity

import (
	"time"
)

const TableNameCharactersDO = "characters"

// CharactersDO mapped from table <characters>
type CharactersDO struct {
	ID         int64     `gorm:"column:id;primaryKey;autoIncrement:true" json:"id"`
	GmtCreate  time.Time `gorm:"column:gmt_create;default:CURRENT_TIMESTAMP" json:"gmt_create"`
	GmtUpdate  time.Time `gorm:"column:gmt_update;default:CURRENT_TIMESTAMP" json:"gmt_update"`
	BirthDay   int32     `gorm:"column:birth_day;not null" json:"birth_day"`
	BirthMonth int32     `gorm:"column:birth_month;not null" json:"birth_month"`
	BirthYear  int32     `gorm:"column:birth_year" json:"birth_year"`
	Comment    string    `gorm:"column:comment" json:"comment"`
	Content    string    `gorm:"column:content" json:"content"`
	Gender     string    `gorm:"column:gender" json:"gender"`
	Name       string    `gorm:"column:name;not null" json:"name"`
	OriginName string    `gorm:"column:origin_name;not null" json:"origin_name"`
	OtherName  string    `gorm:"column:other_name" json:"other_name"`
}

// TableName CharactersDO's table name
func (*CharactersDO) TableName() string {
	return TableNameCharactersDO
}