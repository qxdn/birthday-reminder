package main

import (
	"fmt"

	"github.com/qxdn/birthday/config"
	"github.com/qxdn/birthday/dal/query"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

func main() {
	var config = config.ReadConfig()
	dbConfig := config.Config.DB
	db, err := gorm.Open(mysql.Open(dbConfig.GetDsn()))
	if err != nil {
		panic(err)
	}
	query.SetDefault(db)
	user, _ := query.User.Where(query.User.Id.Eq(1)).First()
	fmt.Println(user)
	character, _ := query.Character.Where(query.Character.Id.Eq(4953)).First()
	fmt.Println(character)
}
