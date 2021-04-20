# SBC Tarefa A Backend

Repositório da aplicação de Backend da equipa TP5-G1(Nao tenho a certeza :3).
Esta API destina-se ao processamento de factos e devolução de uma resposta resultante de um SBC no âmbito da tarefa SmartFood.

## Descrição Simples:
Para interação com o SBC desenvolvido em prolog a equipa decidiu desenvolver um serviço REST API em java utilizando Spring Boot
O uso desta API baseia-se em pedidos HTTP que podem ser feitos em qualquer plataforma dando assim flexibilidade à equipa para desenvolver qualquer tipo de interface sem restrições.
### Forma de Utilização:
Para utilizar a nossa API apenas tem de ir inserindo factos utilizando a rota para inserção de factos e quando estiver pronto para receber uma resposta do SBC apenas utilize a rota de resposta.
#### GETS:
```
/assert?fact={fact}         -> Adiciona um facto em memória no SBC.
/res                        -> Retorna  uma resposta baseada nos factos guardados no SBC.
```
### Perguntas Frequentes:
```
Posso adicionar factos repetidos? -> Pode porém não faz qualquer diferença para a resposta no SBC.
Como sei que já não existem factos no SBC quando for a adicionar mais? -> O SBC possui um sistema de garbage collector que limpa todo o sistema no fim de uso.
Como vamos utilizar esta REST API? -> A equipa pretende utilizar a REST API como backend para uma interface desenvolvida em node.js
```
### Funcionamento do SBC:
```
O SBC neste momento encontra-se em funcionamento e dividido em x ficheiros:

database.pl                 -> Onde residem todas as respostas em forma de perfis alimentares.
baseconhecimento.pl         -> Onde reside toda a lógica na recomendação e atribuição de perfis aos factos inseridos.
forward.pl                  -> Código que permite o assert de factos em memória no SBC.
res.pl                      -> Onde residem as funções utilizadas pela REST API. Podem ser chamadas "controllers" do SBC.
certainty.pl & proof.pl     -> Código fornecido pelo professor que torna possível o funcionamento do SBC com o forward.pl
```
## Sobre o projeto:
```
Para mais informação sobre Prolog visite https://www.swi-prolog.org/
Para mais informação sobre Spring visite https://spring.io/guides/gs/spring-boot/
Para mais informação sobre Nodejs visite https://nodejs.org/en/
```
## Repositório Frontend: 
https://github.com/JoaoGuedes01/sbcFrontend


