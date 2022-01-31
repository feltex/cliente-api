# cliente-api

  API para gerenciar os dados de Clientes. É possível fazer a inclusão, alteração, consulta, listagem e exclusão de clientes.
  Neste projeto mostraremos como armazenar arquivos no MySQL.


Para mais vídeos e tutorias acesso o canal [feltex](https://www.youtube.com/c/FeltexBr)

## Pré-requisitos

1. [Java](https://youtu.be/_NCt_82M0MA)
1. [Maven](https://youtu.be/P29usdprI-E)
1. IDE [Intellij](https://youtu.be/Wsm3lbY8_bI) ou [Eclipse](https://youtu.be/veXmZJdyouQ)
1. [MySQL](https://youtu.be/vramc8g9h_s)
1. [Postman](https://youtu.be/tWGi_QKdGHo)

## Configurar banco de dados MySQL

  Utilizamos o Docker para executar o MySQL neste exemplo. Para aprender mais sobre
Docker assista a nossa playlist sobre [Docker](https://www.youtube.com/playlist?list=PLoBE72jMC_aL9xp7273MaJad-et_5GFph)

 Acesse a pasta docker e execute o comando:
    
    docker-compose up

 Se não quiser utilizar o Docker você precisa ter o MySQL instalado no seu computador e precisará criar um banco de dados 
com o nome `clientedb`. Atualize o usuário e senha no arquivo `application.properties` para os dados do seu banco de dados

    spring.datasource.username=root
    spring.datasource.password=feltex


## Iniciar a aplicação

  Existem algumas formas de iniciar esta API

    java -jar target/cliente-api.jar

ou utilizando o maven

    mvn spring-boot:run


## Criando a imagem Docker da API

    docker build -t andrefelix/cliente-api:V1  .

    docker push andrefelix/cliente-api:V1


## Possíveis problemas

1. Banco de dados não iniciado
```
    The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
            at com.mysql.cj.jdbc.exceptions.SQLError.createCommunicationsException(SQLError.java:174) ~[mysql-connector-java-8.0.25.jar:8.0.25]
            at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:64) ~[mysql-connector-java-8.0.25.jar:8.0.25]
            at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:833) ~[mysql-connector-java-8.0.25.jar:8.0.25]
            at com.mysql.cj.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:453) ~[mysql-connector-java-8.0.25.jar:8.0.25]
            at com.mysql.cj.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:246) ~[mysql-connector-java-8.0.25.jar:8.0.25]
            at com.mysql.cj.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:198) ~[mysql-connector-java-8.0.25.jar:8.0.25]
            at com.zaxxer.hikari.util.DriverDataSource.getConnection(DriverDataSource.java:138) ~[HikariCP-4.0.3.jar:na]
            at com.zaxxer.hikari.pool.PoolBase.newConnection(PoolBase.java:364) ~[HikariCP-4.0.3.jar:na]
            at com.zaxxer.hikari.pool.PoolBase.newPoolEntry(PoolBase.java:206) ~[HikariCP-4.0.3.jar:na]
            at com.zaxxer.hikari.pool.HikariPool.createPoolEntry(HikariPool.java:476) ~[HikariCP-4.0.3.jar:na]
            at com.zaxxer.hikari.pool.HikariPool.checkFailFast(HikariPool.java:561) ~[HikariCP-4.0.3.jar:na]
            at com.zaxxer.hikari.pool.HikariPool.<init>(HikariPool.java:115) ~[HikariCP-4.0.3.jar:na]
            at com.zaxxer.hikari.HikariDataSource.getConnection(HikariDataSource.java:112) ~[HikariCP-4.0.3.jar:na]
            at org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl.getConnection(DatasourceConnectionProviderImpl.java:122) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
            at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess.obtainConnection(JdbcEnvironmentInitiator.java:180) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
            at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:68) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
            at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator.initiateService(JdbcEnvironmentInitiator.java:35) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
            at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:101) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
            at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:263) ~[hibernate-core-5.4.32.Final.jar:5.4.32.Final]
            ...
            at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[spring-beans-5.3.12.jar:5.3.12]
            at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154) ~[spring-context-5.3.12.jar:5.3.12]
            at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:908) ~[spring-context-5.3.12.jar:5.3.12]
            at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583) ~[spring-context-5.3.12.jar:5.3.12]
            at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:145) ~[spring-boot-2.5.6.jar:2.5.6]
            at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754) ~[spring-boot-2.5.6.jar:2.5.6]
            at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:434) ~[spring-boot-2.5.6.jar:2.5.6]
            at org.springframework.boot.SpringApplication.run(SpringApplication.java:338) ~[spring-boot-2.5.6.jar:2.5.6]
            at org.springframework.boot.SpringApplication.run(SpringApplication.java:1343) ~[spring-boot-2.5.6.jar:2.5.6]
            at org.springframework.boot.SpringApplication.run(SpringApplication.java:1332) ~[spring-boot-2.5.6.jar:2.5.6]
            at br.com.feltex.clienteapi.ClienteApiApplication.main(ClienteApiApplication.java:18) ~[classes/:na]
    Caused by: com.mysql.cj.exceptions.CJCommunicationsException: Communications link failure
```

Solução:    
   - Veja o tópico `Configurar banco de dados MySQL` acima.
   
