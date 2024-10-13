package main

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/qxdn/birthday/config"
	log "github.com/sirupsen/logrus"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

const configPath = "./config.yaml"

func initDB(config *config.ApplicationConfig) *gorm.DB {
	dbConfig := config.Config.DB
	if db, err := gorm.Open(mysql.Open(dbConfig.GetDsn()), &gorm.Config{
		DisableForeignKeyConstraintWhenMigrating: true, // 禁用自动创建外键约束
		//Logger:                                   getGormLogger(), // 使用自定义 Logger
	}); err != nil {
		return nil
	} else {
		sqlDB, _ := db.DB()
		sqlDB.SetMaxIdleConns(dbConfig.MaxIdleConns)
		sqlDB.SetMaxOpenConns(dbConfig.MaxOpenConns)
		return db
	}

}

func main() {
	// log设置
	log.SetFormatter(&log.TextFormatter{
		DisableColors: true,
		FullTimestamp: true,
	})
	var config = config.ReadConfig()
	initDB(config)
	// gin设置
	r := gin.Default()
	gin.DefaultWriter = log.StandardLogger().Writer()
	r.GET("/ping", func(c *gin.Context) {
		log.Infof("request:%v", c.Request)
		c.JSON(http.StatusOK, gin.H{
			"message": "pong",
		})
	})
	r.Run(fmt.Sprintf(":%d", config.Config.App.Port)) // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
