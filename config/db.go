package config

import "strconv"

type DB struct {
	Driver              string `json:"driver" yaml:"driver"`
	Host                string `json:"host" yaml:"host"`
	Port                int    `json:"port" yaml:"port"`
	Database            string `json:"database" yaml:"database"`
	UserName            string `json:"username" yaml:"username"`
	Password            string `json:"password" yaml:"password"`
	Charset             string `json:"charset" yaml:"charset"`
	MaxIdleConns        int    `json:"max_idle_conns" yaml:"max_idle_conns"`
	MaxOpenConns        int    `json:"max_open_conns" yaml:"max_open_conns"`
	LogMode             string `json:"log_mode" yaml:"log_mode"`
	EnableFileLogWriter bool   `json:"enable_file_log_writer" yaml:"enable_file_log_writer"`
	LogFilename         string `json:"log_filename" yaml:"log_filename"`
}

// GetDsn 获取数据库连接
func (db *DB) GetDsn() string {
	return db.UserName + ":" + db.Password + "@tcp(" + db.Host + ":" + strconv.Itoa(db.Port) + ")/" +
		db.Database + "?charset=" + db.Charset + "&parseTime=True&loc=Local"

}
