
# API Para Coleta de Dados da Agricultura

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

Api para o armazenamento de dados referente aos sistema de controle de horta hidropônica por meio de seus sensores.

## Sumário

- [Dependências](#dependências)
- [Configurações Iniciais](#configurações-iniciais)
- [Documentação Swagger](#documentação-swagger)
- [Executar Aplicação](#executar-aplicação)

## Dependências
* Java - 17
* Maven - 3.6.3
* PostgreSQL - 14.15

## Configurações Iniciais

### Banco de dados

#### Configurar Base de Dados

Para que a aplicação consiga rodar com sucesso é preciso configurar os dados para realizar a conexão com o PostgreSQL, para isso realize as alterações nas seguintes linhas:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/<nome_db>
spring.datasource.username=<usuario_db>
spring.datasource.password=<senha_db> 
```

Substitua os valores que estão entre <> com os dados de como foi configurado o postgres.

**OBS**: Para o ambiente de teste os dados pode ser os padrões, entretanto, é preciso mudar ao colocar em produção.

## Documentação Swagger

Para facilitar a documentação e a visualização dos endpoints da API, utilizamos o Swagger. Você pode acessar a interface do Swagger através da seguinte URL:
``` 
http://localhost:8080/swagger-ui/index.html
```

## Executar Aplicação
Após realizar todas as configurações acima, para rodar é necessário executar o seguinte comando:
```
mvn clean install
mvn spring-boot:run 
```
**OBS**: A aplicação por padrão está rodando na seguinte url `http://localhost:8080/`

# Executar Dispositivo IoT Emulado

## Pré-Execução
Antes de executar o código em python para simular o dispositivo IoT é preciso criar na mão os sistemas e os usuários. Conferir documentação Swagger para saber como criar os usuários e os sistemas.

##  Como Rodar o Projeto

1. Crie a venv:  

`python -m venv ./venv`

2. Ative o venv

- Linux:

`source venv/bin/activate`

- Windows PowerShell:

`.\venv\Scripts\Activate.ps1`  

- Prompt de Comando (cmd):

`venv\Scripts\activate.bat`

- Git bash:

`source '/caminho/para/dispositivo/iot/venv/Scripts/activate'`

3. Instale as dependências:

`pip install -r requirements.txt`

4. Rodar o aplicativo

`python3 iot.py`
