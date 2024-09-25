package config

import (
	"github.com/fsnotify/fsnotify"
	"github.com/spf13/viper"
)

type Config struct {
	App App `yaml:"app"`
	DB  DB  `yaml:"db"`
}

var config *Config = &Config{}

func ReadConfig() *Config {
	// 配置文件名
	viper.SetConfigName("config")
	// 配置路径
	viper.AddConfigPath(".")
	// 默认配置 端口
	viper.SetDefault("app.port", 8080)
	// 读取配置
	if err := viper.ReadInConfig(); err != nil {
		panic(err)
	}
	// 解析配置
	if err := viper.Unmarshal(config); err != nil {
		panic(err)
	}
	// 监听配置变化
	viper.OnConfigChange(func(e fsnotify.Event) {
		if err := viper.Unmarshal(config); err != nil {
			panic(err)
		}
	})
	viper.WatchConfig()

	return config
}
