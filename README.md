## 本项目实现的最终作用是基于JSP医院在线挂号管理系统
## 分为4个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 修改密码
 - 医生信息管理
 - 医生用户管理
 - 医生预约查询
 - 查询医生信息
 - 查询预约信息
 - 注册用户管理
 - 添加医生信息
 - 科室汇总统计
 - 科室类别信息管理
 - 管理员登录
 - 预约信息管理
 - 预约信息统计
 - 黑名单管理
### 第2个角色为设计文稿，实现了如下功能：
 - 截图1
### 第3个角色为医生角色，实现了如下功能：
 - 修改个人信息
 - 修改登录密码
 - 医生登录
 - 查询预约信息
 - 预约信息管理
### 第4个角色为患者角色，实现了如下功能：
 - 修改个人资料
 - 修改密码
 - 医生查询
 - 患者登录
 - 查看医生信息
 - 查看科室分类
 - 查看预约信息
 - 查看首页
 - 预约医生
## 数据库设计如下：
# 数据库设计文档

**数据库名：** yiyuanguahaosys

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [admin](#admin) |  |
| [fenlei](#fenlei) |  |
| [gh](#gh) |  |
| [member](#member) |  |
| [pmember](#pmember) |  |
| [system](#system) |  |
| [ys](#ys) |  |
| [医生名字](#医生名字) |  |

**表名：** <a id="admin">admin</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  4   | creattime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  5   | flag |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  6   | isuse |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  7   | logintimes |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | quanxian |   varchar   | 1000 |   0    |    Y     |  N   |   NULL    |   |
|  9   | sf |   varchar   | 255 |   0    |    Y     |  N   |   '医生'    |   |

**表名：** <a id="fenlei">fenlei</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | fl |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="gh">gh</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | ysid |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | xm |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 姓名  |
|  4   | ks |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | sj |   date   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  6   | lxr |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 联系人  |
|  7   | lxfs |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  8   | tjsj |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  9   | member |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  10   | bh |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  11   | fy |   float   | 13 |   0    |    Y     |  N   |   NULL    |   |
|  12   | xs |   varchar   | 255 |   0    |    Y     |  N   |   '10-11点'    |   |
|  13   | ysbh |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  14   | zt |   varchar   | 255 |   0    |    Y     |  N   |   '未完成'    |   |

**表名：** <a id="member">member</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  4   | TYPE |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 类型  |
|  5   | regtime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | ifuse |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  7   | logintimes |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | lasttime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  9   | lastip |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  10   | hmd |   varchar   | 255 |   0    |    Y     |  N   |   '否'    |   |

**表名：** <a id="pmember">pmember</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | mid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | realname |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 真实名字  |
|  4   | sex |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 性别  |
|  5   | bir |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | sheng |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | city |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 城市名称  |
|  8   | telphone |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  9   | email |   varchar   | 45 |   0    |    Y     |  N   |   NULL    | 邮箱  |
|  10   | question |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  11   | answer |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  12   | ADDRESS |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 地址  |

**表名：** <a id="system">system</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | sitename |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | url |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 网络地址  |
|  4   | keyword |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | description |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 描述  |
|  6   | email |   varchar   | 45 |   0    |    Y     |  N   |   NULL    | 邮箱  |
|  7   | state |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 状态  |
|  8   | reasons |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  9   | dir |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  10   | record |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  11   | copyright |   text   | 65535 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="ys">ys</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | xm |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 姓名  |
|  3   | ks |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | pic |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 图片  |
|  5   | fy |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | zc |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | nl |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  8   | content |   text   | 65535 |   0    |    Y     |  N   |   NULL    | 内容  |
|  9   | sj |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  10   | bh |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="医生名字">医生名字</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | xm |   varchar   | 50 |   0    |    Y     |  N   |   NULL    |   |
|  3   | ks |   varchar   | 200 |   0    |    Y     |  N   |   NULL    |   |
|  4   | pic |   varchar   | 200 |   0    |    Y     |  N   |   NULL    |   |
|  5   | fy |   varchar   | 200 |   0    |    Y     |  N   |   NULL    |   |
|  6   | zc |   varchar   | 200 |   0    |    Y     |  N   |   NULL    |   |
|  7   | nl |   varchar   | 50 |   0    |    Y     |  N   |   NULL    |   |
|  8   | content |   text   | 65535 |   0    |    Y     |  N   |   NULL    |   |
|  9   | sj |   varchar   | 500 |   0    |    Y     |  N   |   NULL    |   |
|  10   | bh |   varchar   | 500 |   0    |    Y     |  N   |   NULL    |   |

**运行不出来可以微信 javape 我的公众号：源码码头**
