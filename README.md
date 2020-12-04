# API REST Java para Cadastro de Produtos
Projeto exemplo de uma API REST em JAVA utilizando o Framework Spring Boot e banco de dados relacional MySQL para inclusão, alteração, listagem e exclusão de produtos. 

## Métodos
- GET /products - Lista todos os produtos

- GET /products/{id} - Busca um produto por id

- POST /products - Cria um novo produto

- PUT /products/{id} - Edita um produto

- DELETE /products/{id} - Deleta um produto

## Autenticação
Utilizado o Spring Security para autenticação via TOKEN de forma que API trabalhe de forma STATELESS sendo requisitado o TOKEN apenas para métodos POST, PUT e DELETE

Para autenticação, deve ser utilizado as seguintes credenciais em uma requisição POST JSON para a URI <b>/api/auth</b>: 

```
{
    "user": "robertopmassoni@gmail.com",
    "password": "12345"
}
```

O resposta desta requisição retornará um token válido por 10 dias e poderá ser usado para realizar requisições futuras.

## Paginação
A paginação para do método GET <b>/api/products</b> que lista todos os produtos pode ser utilizada opcionalmente através dos parâmetros <b>page, size e sort</b> do Spring Data.

## Inicializando no Docker
Para iniciar o projeto em um container, basta executar os seguintes comandos no mesmo diretório do arquivo <b>docker-compose.yml</b>:

```
docker-compose build
docker-compose up
```

## Inicializando Localmente
Para inicializar o projeto localmente, deve ser importado o banco de dados disponível na pasta <b>database</a> e em seguida executar o jar do projeto utilizando o servidor Tomcat incubado.

```
java -jar xyinc-0.0.1-SNAPSHOT.jar
```
