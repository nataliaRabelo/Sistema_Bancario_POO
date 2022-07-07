# 1. Projeto WinxBank

O WinxBank trata-se de um sistema desenvolvido para implementar um projeto piloto de um sistema bancário digital. O objetivo é fornecer esta implementação como entrega do trabalho final de tema de escolha livre para a disciplina Programação Orientada a Objetos da Universidade Federal Fluminense (UFF), podendo ser extendido para outros fins posteriores.

O WinxBank possui dois grandes objetivos, o de produto e o de projeto, descritos abaixo:

Objetivo do produto: Proporcionar a entrega do trabalho da disciplina de Programação Orientada a Objetos da UFF.

Objetivo do projeto: Fomentar o aprendizado de novas arquiteturas, tecnologias e métodos de Desenvolvimento de sistemas para bancos digitais através da experimentação.

Data: 07/06/2022

Versão atual: 0.1 (manter o pom.xml sempre atualizado)

# 2. Pré-requisitos

* [IntelliJ Community na última versão](https://www.jetbrains.com/idea/download/#section=windows) 
* [Java 17.0.1](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven 3.8.5](https://maven.apache.org/download.cgi)

# 3. Instruções para execução

## 3.1. Compilando como JAR para distribuição

A compilação do pacote pode ser feita através da IDE ou com o comando:

```
mvn install
```

## 3.2. Construindo e Executando diretamente

Você pode executar em modo produção ou debug diretamente através da IDE. Você também pode compilar e executar através do terminal:

```
mvn clean compile exec:java
```

# 4. Estrutura deste Repositório

**EM Construção**

Este repositório está estruturado da seguinte forma:

* src - Código fonte da aplicação de integração
  * src/br.winxbank/ - Fonte principal da aplicação
    * /exception/ - Excecoes do projeto
    * /geradordedocumentos/ - Camada referente à geração de documentos requisitados pelo cliente
    * /random/ - Camada referente à Geração de numeros aleatorios
    * /repository/ - Camada de persistência de dados em arquivos
    * /sistemabancario/ - Camada com classes do sistema bancário
    * /sistemaclientes/ - Camada com classes do sistema de clientes
    * /tempo/ - Camada responsável por controlar uma simulação de tempo.