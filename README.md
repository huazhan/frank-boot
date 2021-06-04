# frank-boot

#### 介绍
##### 前言：
这是一款基于SpringBoot+SpringSecurity+Mybatis的RBAC权限管理系统,目前只做了简单的权限功能，经简单测试功能基本正常

##### 说明：
本项目目前只适合作为一个学习项目，暂不建议用于实际开发，未经过系统全面测试，肯定存在着不少的bug

#### 演示地址：[http://frank.chenhuazhan.com/](http://frank.chenhuazhan.com/)

##### 系统功能：
- 用户管理：提供系统用户配置及角色关联
- 角色管理：提供角色与菜单权限关联
- 菜单管理：提供系统菜单及目录及按钮配置操作
- 部门管理：提供系统部门配置
- 日志管理：提供登录日志，操作日志查询功能
- 监控管理：提供系统服务信息监控功能

##### 技术及框架选型：
- Java1.8
- SpringBoot2.4.1
- SpringSecurity
- MyBatis
- MySql
- Druid
- 前端  Layui
- 前端基于layui的   pear admin layui
- ...

##### 项目中初始用户和密码：
用户：admin 密码：123456
新建用户的密码统一默认：123456
目前修改密码功能暂未开发


#### 安装教程
1.  下载源码
2.  在项目目录下找到初始化建表数据文件 frank-dev.sql 并导入你自己的mysql数据库中
3.  修改配置文件的数据库链接信息
4.  启动项目，在浏览器中输 http://localhost:8089/ 即可访问系统

#### 系统截图
![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114200_461c1d0b_7897827.png "微信截图_20210604113916.png")![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114217_3c7d8377_7897827.png "微信截图_20210604113951.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114235_8fcfe93a_7897827.png "微信截图_20210604114002.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114244_e9d38b2c_7897827.png "微信截图_20210604114020.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114257_430626be_7897827.png "微信截图_20210604114027.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114342_6c54f1f0_7897827.png "微信截图_20210604114036.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114357_caafd23a_7897827.png "微信截图_20210604114045.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114411_5ed5dd07_7897827.png "微信截图_20210604114053.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0604/114422_d90f4c21_7897827.png "微信截图_20210604114103.png")

