项目说明
renren-admin的SpringBoot版，该版本使用JDK10、Mysql5.7

项目结构
sys-admin
├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─app API接口模块(APP调用)
│  ├─job 定时任务模块
│  ├─oss 文件服务模块
│  └─sys 权限模块
│ 
├─RenrenApplication 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  └─static 静态资源



本地部署
通过git下载源码
创建数据库sys-admin，数据库编码为UTF-8
执行doc/db.sql文件，初始化数据【按需导入表结构及数据】
修改application-dev.yml，更新MySQL账号和密码
修改application.yml,更新redis的用户名和密码以及端口
Eclipse、IDEA运行RenrenApplication.java，则可启动项目
项目访问路径：http://localhost:8080/
账号密码：admin/admin
swagger文档路径：http://localhost:8080/swagger/index.html

项目特点
实现前后端分离，通过token进行数据交互，前端再也不用关注后端技术
引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
引入swagger文档支持，方便编写API接口文档
