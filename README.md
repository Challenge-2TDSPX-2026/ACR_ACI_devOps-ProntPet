# ProntPet — Solução DevOps

## 1. Descrição da Solução

O **ProntPet** é uma aplicação desenvolvida para auxiliar no gerenciamento de informações relacionadas ao atendimento veterinário de animais de estimação.

A solução permite o gerenciamento de **tutores, pets, clínicas e consultas veterinárias**, possibilitando o cadastro, consulta, atualização e exclusão de informações por meio de uma API REST desenvolvida em **Java com Spring Boot**.

A aplicação utiliza um banco de dados **MySQL**, responsável pelo armazenamento das informações da plataforma. A estrutura do banco é criada por meio de scripts SQL, enquanto a aplicação utiliza o Spring Data JPA/Hibernate para realizar a comunicação com o banco.

Como parte da solução DevOps, a aplicação foi preparada para execução em **containers Docker**. A imagem da API é armazenada no **Azure Container Registry (ACR)** e executada utilizando **Azure Container Instances (ACI)**. O banco de dados também é executado em um container na infraestrutura da Azure.

A arquitetura permite que a aplicação e o banco de dados sejam executados de forma independente, facilitando a implantação, atualização e gerenciamento dos componentes.

### Principais funcionalidades

* Cadastro e gerenciamento de tutores;
* Cadastro e gerenciamento de pets;
* Cadastro e gerenciamento de clínicas;
* Cadastro e gerenciamento de consultas veterinárias;
* Operações de CRUD através de uma API REST;
* Persistência dos dados em banco MySQL;
* Containerização da aplicação utilizando Docker;
* Armazenamento das imagens no Azure Container Registry;
* Execução dos containers utilizando Azure Container Instances.

---

## 2. Descrição dos Benefícios para o Negócio

O ProntPet busca solucionar problemas relacionados ao **gerenciamento e organização das informações de atendimentos veterinários**, centralizando os dados de tutores, animais, clínicas e consultas em uma única solução.

A utilização de uma API permite que diferentes aplicações ou interfaces possam consumir os mesmos serviços, tornando a solução mais flexível e preparada para futuras expansões.

### Principais benefícios

**Centralização das informações**

Os dados de tutores, pets, clínicas e consultas ficam armazenados de forma estruturada em um banco de dados, reduzindo a dependência de controles manuais e informações dispersas.

**Maior organização dos atendimentos**

A solução permite relacionar pets aos seus respectivos tutores e consultas às clínicas e aos animais atendidos, facilitando o acesso às informações necessárias durante o atendimento veterinário.

**Redução de processos manuais**

As operações de cadastro, consulta, atualização e exclusão podem ser realizadas através da API, reduzindo a necessidade de manipulação manual dos dados.

**Escalabilidade e flexibilidade**

A utilização de containers permite separar a aplicação e o banco de dados em componentes independentes. Isso facilita futuras atualizações, manutenção e expansão da solução.

**Facilidade de implantação**

A containerização com Docker permite que a aplicação seja executada em ambientes padronizados. O uso do Azure Container Registry e do Azure Container Instances facilita a distribuição e execução das imagens na nuvem.

**Disponibilidade na nuvem**

A execução da solução na Microsoft Azure permite disponibilizar a API e o banco de dados em uma infraestrutura acessível remotamente, permitindo que a aplicação seja utilizada sem depender da máquina de desenvolvimento.

**Segurança**

A aplicação foi configurada para não executar o container da API com privilégios administrativos. Além disso, informações sensíveis, como credenciais do banco de dados, são fornecidas por variáveis de ambiente em vez de serem armazenadas diretamente no código-fonte.

---

## Tecnologias utilizadas

* **Java**
* **Spring Boot**
* **Spring Data JPA / Hibernate**
* **MySQL**
* **Docker**
* **Azure Container Registry (ACR)**
* **Azure Container Instances (ACI)**
* **Git / GitHub**

## Objetivo da solução

O objetivo do ProntPet é fornecer uma solução centralizada para o gerenciamento de informações veterinárias, utilizando uma arquitetura baseada em API REST e containers, permitindo maior organização dos dados, facilidade de implantação e utilização de recursos de computação em nuvem.
