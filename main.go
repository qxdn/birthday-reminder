package main

import (
	"fmt"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/qxdn/birthday/config"
	log "github.com/sirupsen/logrus"
	"gopkg.in/yaml.v3"
)

const configPath = "./config.yaml"

func main() {
	// log设置
	log.SetFormatter(&log.TextFormatter{
		DisableColors: true,
		FullTimestamp: true,
	})
	var config config.Config
	// 读取配置
	configFile, err := os.ReadFile(configPath)
	if err != nil {
		log.Fatalf("yamlFile.Get err   #%v ", err)
	}
	err = yaml.Unmarshal(configFile, &config)
	if err != nil {
		log.Fatalf("Unmarshal: %v", err)
	}
	// gin设置
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		log.Infof("config: %v", config)
		c.JSON(http.StatusOK, gin.H{
			"message": "pong",
		})
	})
	r.Run(fmt.Sprintf(":%d", config.App.Port)) // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
