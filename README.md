# 生日提醒服务
每天看到好友在空间发生日提醒，想到自己也可以做一个这样的服务。调研后发现bangumi虽然有详细的角色生日信息，但是图片一般般，不想放男角色（bushi）。acdb虽然提供虽然查询生日查询api但是角色不够，所以自己建一个


顺便用spring-native踩了一些坑

# 效果
<details>
<summary>展开查看</summary>
<img src="./assets/overview.jpg"/>
</details>

# 编译
## java
```shell
mvn clean package -DskipTests # 编译
java -jar target/birthday-reminder-0.0.1-SNAPSHOT.jar # 运行
```
## native
```shell
mvn native:compile -Pnative -DskipTests # 编译
./target/birthday-reminder-0.0.1-SNAPSHOT # 运行
```

# TODO
- [ ] 接入OSS SDK
- [ ] 其他通知类型
- [x] 数据导入
- [x] 简易文档