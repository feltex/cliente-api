# cliente-api

  API para gerenciar os dados de Clientes. É possível fazer a incluisão, alteração, consulta,listagem e exclusão de 
clientes.

  Os dados são armazenados em um banco de dados Relacional MySQL.  



## Configurar banco de dados MySQL

  Utilizamos o Docker para executar o MySQL neste exemplo. Para aprender mais sobre
Docker assista a nossa playlist sobre Docker https://www.youtube.com/playlist?list=PLoBE72jMC_aL9xp7273MaJad-et_5GFph


## Iniciar a aplicação

  Existem algumas formas de iniciar esta API

   li java -jar


## Criando a imagem Docker

    docker build -t andrefelix/cliente-api:V1  .

    docker push andrefelix/cliente-api:V1
