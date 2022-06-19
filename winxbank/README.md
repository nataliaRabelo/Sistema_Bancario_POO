# 1. Projeto WinxBank

O WinxBank trata-se de um sistema desenvolvido para implementar um projeto piloto de um sistema bancaário digital. O objetivo é fornecer esta implementação como entrega do trabalho final de tema de escolha livre para a disciplina Programação Orientada a Objetos da Universidade Federal Fluminense (UFF), podendo ser extendido para outros fins posteriores.

O WinxBank possui dois grandes objetivos, o de produto e o de projeto, descritos abaixo:

Objetivo do produto: Proporcionar a entrega do trabalho da disciplina de Programação Orientada a Objetos da UFF.

Objetivo do projeto: Fomentar o aprendizado de novas arquiteturas, tecnologias e métodos de Desenvolvimento de sistemas para bancos digitais através da experimentação.

Data: 07/06/2022

Versão atual: 0.1 (manter o pom.xml sempre atualizado)

# 2. Pré-requisitos

* Desenvolvimento no Windows 10.
* IDE de livre escolha. Recomendação:[IntelliJ Community na última versão](https://www.jetbrains.com/idea/download/#section=windows) 
* [Java 17.0.1](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven 3.8.5](https://maven.apache.org/download.cgi)
* JUnit 5 (obtido diretamente da IDE)

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

# 4. Estrutura desse repositório

**EM Construção**

Este repositório está estruturado da seguinte forma:

documentos - Documentação anexa deste projeto.
src - Código fonte da aplicação de integração
src/main/ - Fonte principal da aplicação
src/test/ - Fonte do teste de unidade e do teste de estresse
tools - Scripts para execução automática do teste de estresse, geração de javadoc, inicialização, entre outras ferramentar internas desenvolvidas para este projeto

# 5. Estrutura deste Repositório

**EM Construção**

Este repositório está estruturado da seguinte forma:

* documentos - Documentação anexa deste projeto.
* src - Código fonte da aplicação de integração
  * src/main/ - Fonte principal da aplicação
  * src/test/ - Fonte do teste de unidade e do teste de estresse

# 6. Teste unitário do Projeto

**EM Construção**

O código deste projeto é testado através de testes de unidade que fazem a cobertura parcial das componentes presentes no sistema.
O teste de unidade utiliza a biblioteca do JUnit integrada no SpringBoot e todo seu código se encontra em `src/test`. Com a exceção do pacote `/stress`, todo os outros compõe o teste de unidade.
A execuÃ§Ã£o do teste de unidade Ã© feita atravÃ©s do maven. A versÃ£o do JUnit integrada estÃ¡ configurada para ser obtida automaticamente atravÃ©s desta ferramenta.
Todos os testes unitários estão documentados e seguem os seguintes princípios de boas práticas:

* Utilizam o código source da aplicação como caixa preta.
* São independentes entre si.
* São determinísticos.
* Introduzem pouco overhead de tempo de execução e uso de memória comparado ao código fonte testado.
* Uma classe do teste unitário deve testar um conjunto de classes do código fonte com responsabilidades similares.
* As unidade são métodos de alguma classe testada. Cada método deve ser testado como caixa preta.
* Os fluxos de exceções também devem ser considerados nos testes.
* Preferencialmente, cada teste deve testar um único método de uma classe. A exceção é quando a unidade em si utiliza diversos outros métodos firmemente acoplados (sem condicional).