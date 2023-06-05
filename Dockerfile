# 指定基礎鏡像
FROM openjdk:17-jdk-slim
RUN apt-get update && \
    apt-get install -y maven

# 設定環境變量
ENV LINE_BOT_CHANNEL_SECRET=615144659fe0f4dca4a95b0230511783
ENV LINE_BOT_CHANNEL_ACCESS_TOKEN=4jsCVWDFyUMeFNVzI0WckGmVfpEAh3xaHj8SbVnrdpu2hec1HZC2LN2qrPOMW5vuj15/GbCSvygt+FkghyXuXNmtHh7hg9ORZHab7J1V893d5ewWWZasJ53ZnoI2uWTM4i+T/1N0tQnkWsyEW5RsDwdB04t89/1O/w1cDnyilFU=

# 設定工作目錄
WORKDIR /app

# 複製應用程序代碼到容器中
COPY . /app

# 將 gradlew 腳本複製到容器中
COPY gradlew /app/
RUN chmod +x /app/gradlew

# 安裝 Maven 並編譯應用程序代碼
RUN ls -al /app
RUN ./gradlew clean build

# 暴露應用程序端口
EXPOSE 8080

# 啟動應用程序
CMD ["java", "-jar", "build/libs/mybot-0.0.1-SNAPSHOT.jar"]



