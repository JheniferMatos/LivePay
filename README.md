<div align="center">
<img src="https://solutis.com.br/wp-content/uploads/2018/04/logo_solutis_401-2.png">
</div>
<h1 align="center">Desafio SOLUTIS - SQUAD 4<br>Cabou o Money</h1>

<div align="center">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
    <img src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white">
</div>
<p align="center"><br>
    <img src="http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge">
</p>
<p>Este repositório contém o código-fonte e a implementação de um sistema de backend em Java para um ecommerce voltado para livestreams.</p>

Trello: [https://trello.com/b/QvGBf6JC/desafio-locadora-solutis](https://trello.com/b/qjC12Ih4/squad-4)

## Obejtivo

<p> O objetivo deste projeto é fornecer uma plataforma rápida e escalavel para gerenciar a compra e venda em uma plataforma de ecommerce.</p>

## Funcionalidades Principais

- **Gestão de Pedidos:** Cadastro, atualização e remoção de pedidos no sistema. Cada pedido possui uma lista de produtos.

- **Gestão de Produtos:** Cadastro, atualização e remoção de produtos no sistema.

- **Gestão de Pagamentos:** Registro e manutenção de dados dos pagamentos da plataforma.

- **Envio de Email:** Os clientes são notificados ao realizar pagamentos na plataforma.

## Tecnologias Utilizadas

- **Java:** v20.0 - Linguagem de programação principal para a lógica de negócios e manipulação de dados.

- **Spring Boot:** v3.1.2 - Facilita a configuração e criação de aplicações Spring, reduzindo a necessidade de configurações manuais.

- **Maven** v4.0.0 - Gerenciador de dependencias, facilitando a implementação e gerenciamento de bibliotecas.

- **Spring Data JPA:** - Abstração para o acesso e manipulação de dados no banco de dados, facilitando a persistência dos objetos Java.

- **Banco de Dados MySql:** - Utilizado para armazenar informações sobre veículos, clientes, reservas e aluguéis.

- **Lombok** v1.18.28 - Ferramenta utilizada para amentar a produtividade.

- **IntelliJ IDEA** - IDE utilizada pela equipe para densenvolvimento do projeto.

- **RabbitMQ** - Mensageria assincrona utilizada no projeto
  
- **Arquitetura de Microsservices** - Arquitetura do projeto escolhida

- **Junit5** - Testes unitarios

- **Locust** - Testes de carga

- **Spring Cloud** - Utilizado para configurar o gateway e rotas

- **Spring Security e JWT** - Segurança feita com geração de tokens JWT

- **Swgager e Javadoc** - Documentação feita com essas bibliotecas



## Estrategias de Desenvolvimento 

- **Arquitetura de microsservices** - Arquitetura escolhida pela escalabilidade, resiliencia e facilidade de manutenção

<img width="455" alt="Captura de Tela 2023-11-20 às 14 17 21" src="https://github.com/JheniferMatos/LivePay/assets/95923686/8104c69a-9afb-4533-ba1d-09132e713f85">
<br>

- **Spring Security** - Foi utilizado o spring security juntamente com o JWT para gerar tokens de acesso, foi escolhido pela facilidade de integração, segurança e desempenho

<img width="850" alt="Captura de Tela 2023-11-20 às 14 20 42" src="https://github.com/JheniferMatos/LivePay/assets/95923686/bc60c641-9f6d-4971-8051-ae678c9db060">
<br>

- **Comunicação assincrona com RabbiMQ** - Para escalabilidade e desempenho as comunicações entre os microsserviços são assincronas

<img width="1137" alt="Captura de Tela 2023-11-20 às 14 31 34" src="https://github.com/JheniferMatos/LivePay/assets/95923686/15a94f9e-61be-4ab5-bab9-972995f20a81">
<br><br>
<img width="1042" alt="Captura de Tela 2023-11-20 às 14 43 15" src="https://github.com/JheniferMatos/LivePay/assets/95923686/4e4786f1-b244-4a73-a58c-bed93f67fc49">
<br>

- **Testes Unitarios e de Carga** - Estratégia utilizada para testar o funcionamento esperado dos componentes e a resistencia do sistema


<img width="1465" alt="Captura de Tela 2023-11-09 às 23 31 11" src="https://github.com/JheniferMatos/LivePay/assets/95923686/55afc2d5-bc44-4372-a4d7-abfa35f03b9b">
<br><br>
<img width="911" alt="Captura de Tela 2023-11-20 às 15 37 29" src="https://github.com/JheniferMatos/LivePay/assets/95923686/c8af39bd-4830-4c47-ad64-6aac426c5b3c">
<br>

- **Documentação** - Utilizamos o swagger e javadoc para documentar a aplicação

<img width="1459" alt="Captura de Tela 2023-11-20 às 15 50 55" src="https://github.com/JheniferMatos/LivePay/assets/95923686/6ded96b0-97f8-4bd3-aa7a-fdce15186c78">
<br>

## Configuração e Uso

1. Clone este repositório.

2. Configure as informações do banco de dados no arquivo `application.properties`.

3. Execute a aplicação Server primeiro e depois os outros microsserviços.

5. Acesse a API REST em `http://localhost:8765` e utilize as rotas para interagir com o sistema.

6. Verifique também a documentação swagger da aplicação `http://localhost:8080/swagger-ui/index.html#` para ter acesso às rotas.

## Links

- Repositório: https://github.com/JheniferMatos/LivePay

