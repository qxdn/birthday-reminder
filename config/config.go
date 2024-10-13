package config

import (
	"github.com/fsnotify/fsnotify"
	"github.com/spf13/viper"
)

type Config struct {
	App App `yaml:"app" json:"app"`
	DB  DB  `yaml:"db" json:"db"`
}

type ApplicationConfig struct {
	Config      *Config      // 配置
	ConfigViper *viper.Viper // viper
}

// 配置
var config *ApplicationConfig = &ApplicationConfig{
	Config: &Config{},
}

func ReadConfig() *ApplicationConfig {
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
	if err := viper.Unmarshal(config.Config); err != nil {
		panic(err)
	}
	// 监听配置变化
	viper.OnConfigChange(func(e fsnotify.Event) {
		if err := viper.Unmarshal(config.Config); err != nil {
			panic(err)
		}
	})
	viper.WatchConfig()
	return config
}

func (app *ApplicationConfig) GetDsn() string {
	return app.Config.DB.GetDsn()
}
