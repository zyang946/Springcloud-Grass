server:
    port: ${port}
spring:
    cloud:
        nacos:
            discovery:
                server-addr: localhost:8848
    application:
        name: ${name}
    datasource:
        url: jdbc:mysql://localhost:3306/grass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false
        username: root
        password: root123
        driver-class-name: com.mysql.cj.jdbc.Driver
    jpa:
        hibernate:
            ddl-auto: update
        properties:
            hibernate:
                dialect: org.hibernate.dialect.MySQL5Dialect