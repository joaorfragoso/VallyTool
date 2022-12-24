# VallyTool
## Aplicação criada para informatizar o processo de pré conselho de classe na ETEJK.

![image](https://user-images.githubusercontent.com/86073233/209441740-f81a5a97-20b5-4df0-ab34-901999a35538.png)
![image](https://user-images.githubusercontent.com/86073233/209441756-1e8afa11-087c-4e49-8c7e-738c07d24dda.png)
![image](https://user-images.githubusercontent.com/86073233/209441772-d5acc514-d45e-4f47-87fc-7369b60fdb32.png)
![image](https://user-images.githubusercontent.com/86073233/209441787-b12f2f66-196e-483b-9b14-30ccc1e789f2.png)
![image](https://user-images.githubusercontent.com/86073233/209441794-ef1942f5-5105-4daf-9536-68358c44643d.png)
![image](https://user-images.githubusercontent.com/86073233/209441824-720524b3-f241-430c-a147-bb8e73ba7009.png)

# Introdução

### Como projeto final da FAETEC o nosso grupo ficou encarregado de criar uma solução para os problemas que existiam no SOP (Serviço de Educação Pedagógica) em relação à realização do pré-conselho de classe.
### O sistema que os funcionários usavam era de planilhas no Excel para informar a situação das turmas, que chegava por meio das imensas papeladas que vinham dos professores.
### A solução foi criar uma plataforma web para resolver tanto a situação dos professores, que precisavam escrever muito, tanto a situação dos funcionários que precisavam transcrever à mão as informações que vinham dos professores para uma planilha do Excel.

# Projeto

## A base da aplicação é a existência de 2 tipos de usuários.

> ## ***Professor***
> - Encarregado de avaliar as disciplinas das turmas do qual foi relacionado.

> ## ***Admin (SOP)***
> - Encarregado de relacionar professores nas suas respectivas turmas e disciplinas.
> - Fechar o trimestre.
> - Remover usuários ou Adicionar novos, podendo ser do tipo Admin ou Professor.
> - Adicionar turmas e disciplinas.
> - Gerenciar os resultados trimestrais.
> - Emitir PDF com todos os resultados do trimestre.

# Arquitetura e Stack

Feito em uma arqutetura monolitica, com todos os compoonentes ligados a uma unica aplicação Spring Boot.

Os serviços estão organizados em arquitetura de camadas hexagonal, clique para ver o [modelo](https://user-images.githubusercontent.com/86073233/209199986-478711fc-afd2-47ae-8887-4e2a700dc5f9.png).

> ## Detalhes de stack   
> ***A implementação do serviço é feita em [Java 17](https://docs.oracle.com/en/java/javase/17/) usando [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/).*** 
> - A persistência de dados deste serviço é feita em um banco relacional [MySQL](https://dev.mysql.com/doc/). [Modelo de Dados]()  
> 
> No Front-end é usado as seguintes tecnologias:
> - HTML5
> - CSS3
> - [JavaScript](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript)
>
> ***[Bibliotecas Usadas]()***


# Contribuidores do Projeto

> ## [João Victor](https://github.com/joaorfragoso)
> - Design e Front-End da Aplicação


> ## [Marcos Guimarães](https://github.com/joaorfragoso)
> - Modelagem de Dados, Arquitetura e Back-End da Aplicação

