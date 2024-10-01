package config

type DB struct {
	DSN string `yaml:"dsn" json:"dsn"` // 数据库连接
}
