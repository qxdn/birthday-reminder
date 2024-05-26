---
outline: deep
---

# 生日查询API

## 说明
所有生日数据来源于bangumi 2024-05-07的dump数据，所有dump数据在[github](https://github.com/bangumi/Archive/releases/tag/archive)。经过简单的数据清洗，具体为提取年月日，过滤性别，去掉部分furry。

图片数据大多来源于bangumi的图片，部分来自网络收集（这个才是这个项目成立的真正的原因，之前每天看到群友天天发生日祝福顺便带图的时候我就想着自己收集一份了）

## 请求说明 
- Method: **GET**
- URL: `https://birthday.qxdn.fun/api/character/birthday`

## 请求参数
| 参数名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| year | int | 年份 | null | 否 |
| month | int | 月份 | null | 否 |
| day | int | 日期 | null | 否 |
| needYear | bool | 当年月日都为空时，会默认为当前日期，此时通过该参数判断是否需要将当前年加入查询 | false | 否 |


## 返回结果
| 参数名 | 类型 | 说明 
| --- | --- | --- |
| data | list | 角色,见角色详情 |
| errorCode | string | 错误码，0为成功 |
| errorMessage | string | 错误信息 |
| success | bool | 是否成功 |
| total | int | 总数，分页查询时用 |

### 角色详情
| 参数名 | 类型 | 说明
| --- | --- | --- |
| id | int | id |
| name | string | 简中姓名 |
| originName | string | 原名 |
| gender | string | 性别 M:男，F:女 |
| otherName| list[string] | 别名 |
| images | list[string] | 图片链接 |
| comment | string | 备注 |
| gmtCreate | datetime | 创建时间 | 
| gmtUpdate | datetime | 更新时间 | 

## 样例
```json
// https://birthday.qxdn.fun/api/character/birthday?month=7&day=25&year=1992
{
  "data": [
    {
      "id": 1033,
      "name": "牧濑红莉栖",
      "originName": "牧瀬紅莉栖",
      "gender": "F",
      "otherName": [
        "Chris Makise",
        "牧瀬 紅莉栖",
        "まきせ くりす",
        "Makise Kurisu",
        "天才少女，THE ZOMBIE/复苏者，克里斯蒂娜，天才，天才变态少女，放荡天才变态少女，助手，变态，セレブ・セブンティーン/Celeb Seventeen/土豪十七，セレセブ/Celeb Sev/土七，最爱实验妹， 蒙古斑少女，@Channeler，Dr.Pepperian，克里斯蒂娜博士",
        "克里斯蒂娜/クリスティーナ/Christina",
        "粟悟饭和龟波功（栗悟飯とカメハメ波）（网名）"
      ],
      "birthday": "1992-07-25",
      "images": [
        "https://lain.bgm.tv/pic/crt/l/f7/2a/12393_crt_SS7II.jpg?r=1531372585"
      ],
      "comment": null,
      "gmtCreate": "2024-05-19T04:25:07.000+00:00",
      "gmtUpdate": "2024-05-19T04:25:07.000+00:00"
    }
  ],
  "errorCode": "0",
  "errorMessage": "成功",
  "success": true,
  "total": null
}
```

## 错误码
等待编辑