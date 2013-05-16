@echo off
echo [INFO] 确保默认JDK版本为JDK6.0及以上版本,已配置JAVA_HOME.
echo [INFO] 确保已安装Maven与Ant，并在Ant lib中增加maven-ant-tasks-2.*.*.jar.
echo [INFO] 确保网络保持畅通，能连接Maven官方网站.

set MVN=mvn
set ANT=ant
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m

echo [Step 1] 复制tools/springside 到 %userprofile%\.m2\repository
xcopy /s/e/i/h/d/y "tools\springside" "%USERPROFILE%\.m2\repository\org\springside"

echo [Step 2] 启动H2数据库.
cd tools/h2
start "H2" %MVN% exec:java
cd ..\..\

echo [Step 3] 为TaskFlow 初始化数据库, 启动Jetty.
call %ANT% -f bin/db/build.xml init-db
start "为TaskFlow" %MVN% %OFF_LINE% jetty:run

echo [INFO] TaskFlow0.1 快速启动完毕.
echo [INFO] 可访问以下演示网址:
echo [INFO] http://localhost:9090/taskflow

:end
pause