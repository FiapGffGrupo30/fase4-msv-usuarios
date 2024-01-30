# **Usuários - GFF**

Api responsável por gerenciar os usuários da aplicação de Sistema de Gerenciamento de Fast Food.

## **Objetivo**
A API de usuários possui as ferramentas que possibilitam ao cliente, criar, editar, pesquisar, alterar e deletar usuários.

## **Tecnologias utilizadas**
- [Git](https://git-scm.com/downloads)
- [Java - Versão 17](https://www.oracle.com/java/)
- [Maven](https://maven.apache.org/)
- [Docker](https://docs.docker.com/)
- [MySql](https://www.mysql.com/)
- [Swagger](https://swagger.io/docs/specification/about/)

<br>

## **Subindo a Aplicação**

Para buildar a aplicação atarvés do download do programa via git é necessário ter as tecnologias Git, Java (versão 17 - sdk 17) e Docker instalados.
Após instalar as tecnologias segua os seguintes passos:
<br>
- 1° - Baixe o diretório para uma pasta local de sua máquina.
- 2° - Abra o terminal no diretório na qual você fez o download da pasta do programa.
- 3° - Utilize o comando a seguir para para buildar a imagem localmente
<br>

```bash
    docker build -t gffusuarios:latest .
```
4º Após realizar o build é possivel executar a imagem usando o seguinte comando:

```bash
docker container run gffusuarios
```

Para acessar o Swagger da aplicação acesse em um browser de sua preferência o link:
<br>

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

No swagger você encontrará as rotas de utilização que a API proporciona.
