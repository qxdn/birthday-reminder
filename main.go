package main

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/qxdn/birthday/config"
	log "github.com/sirupsen/logrus"
)

const configPath = "./config.yaml"

func main() {
	// log设置
	log.SetFormatter(&log.TextFormatter{
		DisableColors: true,
		FullTimestamp: true,
	})
	var config = config.ReadConfig()
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
