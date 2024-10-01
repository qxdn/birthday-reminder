package config

type App struct {
	Name string `yaml:"name" json:"name"` // 名称
	Port int    `yaml:"port" json:"port"` // 端口
}
