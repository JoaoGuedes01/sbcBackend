<br>
<p align="center">
  <a href="https://www.uminho.pt" target="_blank"><img src="https://i.imgur.com/FXQo8OL.png" alt="Universidade do Minho"></a>
  <a href="https://www.eng.uminho.pt" target="_blank"><img src="https://i.imgur.com/WABo4st.png" alt="Escola de Engenharia"></a>
  <br><a href="http://www.dsi.uminho.pt" target="_blank"><strong>Departamento de Sistemas de Informação</strong></a>
  
  <h2 align="center">Projeto Prá* [Tu](mailto:tu@gmail.com)tico SBC - MIEGSI 2020/2021</h2>
  <br>
  
## Índice de conteúdos

- [Índice de conteúdos](#índice-de-conteúdos)
- [Introdução <a name = "intro"></a>](#introdução-)
- [Tarefa A - Aconselhamento para compra de uma refeição <a name = "ta"></a>](#tarefa-a---aconselhamento-para-compra-de-uma-refeição-)
  - [Parte 1 - Aquisição manual de conhecimento  <a name = "ta1"></a>](#parte-1---aquisição-manual-de-conhecimento--)
  - [Parte 2 - Aquisição automática de conhecimento <a name = "ta2"></a>](#parte-2---aquisição-automática-de-conhecimento-)
  - [Pré-requisitos <a name = "pre1"></a>](#pré-requisitos-)
- [Getting started <a name = "getting1"></a>](#getting-started-)
  - [Quick-start <a name = "quick1"></a>](#quick-start-)
    - [Start your application ....](#start-your-application-)
  - [Installation  <a name = "install1"></a>](#installation--)
    - [Add SWI-Prolog to the PATH environment variable](#add-swi-prolog-to-the-path-environment-variable)
    - [Clone the repo](#clone-the-repo)
    - [Install all the dependecies](#install-all-the-dependecies)
  - [Usage  <a name = "usage1"></a>](#usage--)
- [Tarefa B - Aconselhamento de trajeto para entrega de uma refeição <a name = "tb"></a>](#tarefa-b---aconselhamento-de-trajeto-para-entrega-de-uma-refeição-)
  - [Parte 1 - Resolução via Procura <a name = "tb1"></a>](#parte-1---resolução-via-procura-)
  - [Parte 2 - Otimização do lucro, tempo ou ambos <a name = "tb2"></a>](#parte-2---otimização-do-lucro-tempo-ou-ambos-)
  - [Pré-requisitos <a name = "pre2"></a>](#pré-requisitos--1)
- [Getting started <a name = "getting2"></a>](#getting-started--1)
  - [Quick-start <a name = "quick2"></a>](#quick-start--1)
    - [Clone the repo](#clone-the-repo-1)
  - [Usage <a name = "usage2"></a>](#usage-)
- [Ferramentas <a name = "built"></a>](#ferramentas-)
- [Contactos <a name = "contact"></a>](#contactos-)
- [Acknowledgments <a name = "ack"></a>](#acknowledgments-)
- [Referências <a name = "refer"></a>](#referências-)
- [Licensa <a name = "license"></a>](#licensa-)

## Introdução <a name = "intro"></a>

No âmbito da unidade curricular de Sistemas Baseados em Conhecimento, foi-nos proposto a conceção de um SBC implementado na linguagem Prolog, estando a mesma dividida em 2 tarefas com 2 partes cada uma, tendo por base o conceito de food delivery, tão em voga no último ano decorrente da situação pandémica que vivenciamos.
Para tal decidimos dividir a solução em 2 endpoints:
	º Front-end (Node.js) -> Interface gráfica para o utilizador interagir com a aplicação.
	º Back-end (Java Spring Boot) -> Interface de Aplicação Web (Web API) destinada ao processamento dos pedidos HTTP vindos da interface. É est endpoint que executa toda a lógica e interage diretamente com o SBC em prolog.


## Tarefa A - Aconselhamento para compra de uma refeição <a name = "ta"></a>
XXXXXX

### Parte 1 - Aquisição manual de conhecimento  <a name = "ta1"></a>
XXXXX

### Parte 2 - Aquisição automática de conhecimento <a name = "ta2"></a>
XXXXXX

### Pré-requisitos <a name = "pre1"></a>
* [SWI-Prolog 8.2.4](https://www.swi-prolog.org/download/stable)
* [Weka](https://waikato.github.io/weka-wiki/downloading_weka/)
* Outros......


## Getting started <a name = "getting1"></a>

### Quick-start <a name = "quick1"></a>

#### Start your application ....
  ```run .....
  ```

### Installation  <a name = "install1"></a>
#### Add SWI-Prolog to the PATH environment variable
#### Clone the repo
  ```sh
  git clone https://github.com/xxxxxxxxxxxxxxxxxxxxxxxxx.git
  ```
#### Install all the dependecies
  ```sh
  xxxxxx
  ```

### Usage  <a name = "usage1"></a>


## Tarefa B - Aconselhamento de trajeto para entrega de uma refeição <a name = "tb"></a>
Desenvolver um SBC para um estafeta que usa uma scooter como meio de transporte que trabalha para um sistema de entrega de um restaurante. O SBC deve aconselhar que encomendas o estafeta deve pegar no restaurante e qual o caminho a seguir para proceder às entregas. Optamos por desenvolver xxxx.

### Parte 1 - Resolução via Procura <a name = "tb1"></a>
Nesta parte foram desenvolvidas as funcionalidades de procura para o objetivo 1 

### Parte 2 - Otimização do lucro, tempo ou ambos <a name = "tb2"></a>
Nesta parte foram desenvolvidas as funcionalidades de optimização usando o método de hillclimbing para o objetivo A (maximizar o lucro), B (minimizar o tempo do percurso) e C (maximizar 0.8*lucro+0.2*(20-tempo)).XXXXXX


### Pré-requisitos <a name = "pre2"></a>
* Ferramenta IDE para execução de código Backend em Java.
* Node.js para execução da aplicação de Frontend.

## Getting started <a name = "getting2"></a>

### Quick-start <a name = "quick2"></a>

#### Clone the repo
  ```sh
  git clone  https://github.com/JoaoGuedes01/sbcBackend.git
  ```

### Usage <a name = "usage2"></a>
  ```sh
  Correr o Servidor Backend Spring Boot para a Tarefa A é necessário para o uso da aplicação Tarefa A.
  ```
   ```sh
  Correr o Servidor Backend Spring Boot para a Tarefa B é necessário para o uso da aplicação Tarefa B.
  ```
  


  
## Ferramentas <a name = "built"></a>
* [SWI-Prolog](https://www.swi-prolog.org)
* [Node.js](https://nodejs.org/en/)
* [Java](https://www.java.com/en/)
* [Spring Boot](https://spring.io/projects/spring-boot)



## Contactos <a name = "contact"></a>

* [EU](mailto:eu0@hotmail.com)
* [Tu](mailto:tu@gmail.com)
* [Vos](mailto:vos@gmail.com)


## Acknowledgments <a name = "ack"></a>
* [Paulo Cortez](http://www3.dsi.uminho.pt/pcortez/Home.html)
* [André Pilastri](https://pilastri.github.io/andrepilastri.github.io/#about)

## Referências <a name = "refer"></a>

## Licensa <a name = "license"></a>

Distributed under the MIT License. See `LICENSE` for more information.
