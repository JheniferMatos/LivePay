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
<p>Este repositório contém o código-fonte e a implementação de um sistema de backend em Java para um ecommerce voltado para livestreams. O objetivo deste projeto é fornecer uma plataforma para gerenciar a compra e venda em uma plataforma de ecommerce.</p>

Trello: [https://trello.com/b/QvGBf6JC/desafio-locadora-solutis](https://trello.com/b/qjC12Ih4/squad-4)


## Funcionalidades Principais

- **Gestão de Pedidos:** Cadastro, atualização e remoção de pedidos no sistema. Cada pedido possui uma lista de produtos.

- **Gestão de Produtos:** Cadastro, atualização e remoção de produtos no sistema.

- **Gestão de Pagamentos:** Registro e manutenção de dados dos pagamentos da plataforma.

- **Reservas e Aluguéis:** Os clientes podem realizar reservas para aluguel de veículos, especificando datas de início e término.

## Tecnologias Utilizadas

- **Java:** v20.0 - Linguagem de programação principal para a lógica de negócios e manipulação de dados.

- **Spring Framework:** v2.0.2 - Utilizado para a criação de APIs RESTful, gerenciamento de dependências e injeção de dependências.

- **Spring Boot:** v3.1.2 - Facilita a configuração e criação de aplicações Spring, reduzindo a necessidade de configurações manuais.

- **Maven** v4.0.0 - Gerenciador de dependencias, facilitando a implementação e gerenciamento de bibliotecas.

- **Spring Data JPA:** - Abstração para o acesso e manipulação de dados no banco de dados, facilitando a persistência dos objetos Java.

- **Banco de Dados MySql:** - Utilizado para armazenar informações sobre veículos, clientes, reservas e aluguéis.

- **Banco de Dados H2:** - Utilizado para armazenar informações sobre veículos, clientes, reservas e aluguéis em fase de testes.

- **Lombok** v1.18.28 - Ferramenta utilizada para amentar a produtividade na criação de componentes internos.

- **IntelliJ IDEA** - IDE utilizada pela equipe para densenvolvimento do projeto.

- **RabbitMQ**
  
- **Arquitetura de Microsservices** - IDE utilizada pela equipe para densenvolvimento do projeto.

- **Junit5**

- **Locust**

- **Spring Cloud**

- **Spring Security e JWT**

- **Swgager e Javadoc**



## Estrategias de Desenvolvimento 

- **Arquitetura de microsservices** -

<img width="455" alt="Captura de Tela 2023-11-20 às 14 17 21" src="https://github.com/JheniferMatos/LivePay/assets/95923686/8104c69a-9afb-4533-ba1d-09132e713f85">


- **Spring Security** - Foi utilizado o spring security juntamente com o JWT para gerar tokens de acesso, foi escolhido pela facilidade de integração, segurança e desempenho

<img width="850" alt="Captura de Tela 2023-11-20 às 14 20 42" src="https://github.com/JheniferMatos/LivePay/assets/95923686/bc60c641-9f6d-4971-8051-ae678c9db060">


- **Comunicação assincrona com RabbiMQ** - Para escalabilidade e desempenho as comunicações entre os microsserviços são assincronas

<img width="1137" alt="Captura de Tela 2023-11-20 às 14 31 34" src="https://github.com/JheniferMatos/LivePay/assets/95923686/15a94f9e-61be-4ab5-bab9-972995f20a81">
<br><br>
<img width="1042" alt="Captura de Tela 2023-11-20 às 14 43 15" src="https://github.com/JheniferMatos/LivePay/assets/95923686/4e4786f1-b244-4a73-a58c-bed93f67fc49">


- **Testes Unitarios e de Carga** - Estratégia utilizada para testar o funcionamento esperado dos componentes e a resistencia do sistema


<img width="1465" alt="Captura de Tela 2023-11-09 às 23 31 11" src="https://github.com/JheniferMatos/LivePay/assets/95923686/55afc2d5-bc44-4372-a4d7-abfa35f03b9b">
<br><br>
<img width="911" alt="Captura de Tela 2023-11-20 às 15 37 29" src="https://github.com/JheniferMatos/LivePay/assets/95923686/c8af39bd-4830-4c47-ad64-6aac426c5b3c">



## Configuração e Uso

1. Clone este repositório.

2. Configure as informações do banco de dados no arquivo `application.properties`.

3. Execute a aplicação Server primeiro e depois os outros microsserviços.

5. Acesse a API REST em `http://localhost:8765` e utilize as rotas para interagir com o sistema.

6. Verifique também a documentação swagger da aplicação `http://localhost:8080/swagger-ui/index.html#` para ter acesso às rotas.

## Links

- Repositório: https://github.com/ArthurLorenzzo/Desafio-LocadoraDeVeiculos-Squad2

<h2>Desenvolvedores do projeto</h2> 
<table style="width:100%">
  <tr align=center>
    <th><strong>Arthur Lorenzzo</strong></th>
    <th><strong>Guilherme Vaccaro</strong></th>
    <th><strong>Gustavo Silva</strong></th>
    <th><strong>Isabelle Carvalho</strong></th>
    <th><strong>Matheus Araújo</strong></th>
  </tr>
  <tr align=center>
    <td>
      <a href="https://github.com/ArthurLorenzzo">
        <img width="200" height="180" src="https://avatars.githubusercontent.com/u/95923686?v=4">
      </a>
    </td>
    <td>
      <a href="https://github.com/guilhermevaccaro">
        <img width="200" height="180" src="https://avatars.githubusercontent.com/u/109315604?v=4">
      </a>
    </td>
    <td>
      <a href="https://github.com/GustavoSilvalgs">
        <img width="200" height="180" src="https://avatars.githubusercontent.com/u/111322525?v=4">
      </a>
    </td>
    <td>
      <a href="https://github.com/IsabelleCarvallho">
        <img width="200" height="180" src="https://avatars.githubusercontent.com/u/110946274?v=4">
      </a>
    </td>
    <td>
      <a href="https://github.com/mmatheusaraujoo">
        <img width="200" height="180" src="https://avatars.githubusercontent.com/u/106783873?v=4">
      </a>
    </td>
  </tr>
</table>
