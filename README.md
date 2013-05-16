#TaskFlow

Task Flow 是一个任务流转和管理的系统，其中也包含了机构设置与管理和任务查询、报表等内容。

Task Flow 使用springside3 + hibernate3 + spring3 + struts2 进行开发，前台展现选择了JSP而非Free Marker（不过struts2标签的模板使用的是Free Marker）。其中包含了代码表、缓存、页面标签等一些技术特点。

Task Flow 主页:[http://wangpuv.github.io/taskflow](http://wangpuv.github.io/taskflow)

##一键启动说明

1、确保默认JDK版本为JDK6.0及以上版本,已配置JAVA_HOME.

2、确保已安装Maven与Ant，并配置环境变量，另需在Ant lib中增加maven-ant-tasks-2.*.*.jar（执行H2数据库初始化脚本）.

3、确保网络保持畅通，能连接Maven官方网站进行下载.
