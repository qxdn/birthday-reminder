package config

type App struct {
	Name string `yaml:"name"` // 名称
	Port int    `yaml:"port"` // 端口
}
