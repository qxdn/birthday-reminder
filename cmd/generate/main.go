package main

import (
	"strings"

	"github.com/iancoleman/strcase"
	"github.com/qxdn/birthday/config"
	"github.com/qxdn/birthday/model"
	"gorm.io/driver/mysql"
	"gorm.io/gen"
	"gorm.io/gorm"
)

func main() {
	var config = config.ReadConfig()
	dbConfig := config.Config.DB
	db, err := gorm.Open(mysql.Open(dbConfig.GetDsn()))
	if err != nil {
		panic(err)
	}
	g := gen.NewGenerator(gen.Config{
		OutPath:      "dal/query",
		ModelPkgPath: "entity",
		Mode:         gen.WithoutContext | gen.WithDefaultQuery | gen.WithQueryInterface, // generate mode
	})
	// 表生成策略
	g.WithTableNameStrategy(func(tableName string) (targetTableName string) {
		if strings.HasPrefix(tableName, "qrtz_") { //Just return an empty string and the table will be ignored.
			return ""
		}
		return tableName
	})
	g.WithModelNameStrategy(func(tableName string) (targetModelName string) {
		return strcase.ToCamel(tableName) + "DO"
	})

	g.UseDB(db) // reuse your gorm db
	//allModel := g.GenerateAllTable() // generate all tables
	//g.ApplyBasic(allModel...)        // apply basic query
	g.ApplyBasic(model.User{}, model.Character{}, model.Subscriber{}) // apply basic query
	g.Execute()
}
