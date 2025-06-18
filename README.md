# Sistema de Controle de Autorizações de Procedimentos Médicos

Aplicativo Web em Java utilizando JSP (Java Server Pages), Servlets e PostgreSQL, para controle de solicitações de autorização de procedimentos médicos conforme regras de autorização abaixo:

| Procedimento | Idade  | Sexo |Permitido |
| ------------ | ------ |----- |----------|
| 1234         | 10     | M    | NÃO      |
| 4567         | 20     | M    | SIM      |
| 6789         | 10     | F    | NÃO      |
| 6789         | 10     | M    | SIM      |
| 1234         | 20     | M    | SIM      |
| 4567         | 30     | F    | SIM      |
---

## 🚀 Tecnologias Utilizadas

* Java 17
* Jakarta EE (Servlets, JSP)
* WildFly 36.0.1 (standalone)
* PostgreSQL 17
* Liquibase
* Maven
* Docker + Docker Compose
* JQuery + Bootstrap

---

## 🧪 Compilação e Execução

### 1. Compilar o projeto

```bash
mvn clean package
```

Gera o artefato `.war` em:

```
target/controle-autorizacao-procedimento-medico.war
```

---

### 2. Rodar com Docker

#### A. Usando Docker Compose (para app + banco)

Crie o seguinte arquivo `docker-compose.yml` na raiz do projeto:

```yaml
version: '3.9'

services:
  postgres:
    image: postgres:17
    restart: always
    environment:
      POSTGRES_USER: seu_usuario_aqui
      POSTGRES_PASSWORD: sua_senha_aqui
      POSTGRES_DB: autorizador_procedimentos
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
    networks:
      - app-net

  wildfly:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: wildfly-app
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    networks:
      - app-net

volumes:
  pgdata:

networks:
  app-net:
```

#### B. Dockerfile personalizado para WildFly

Crie um arquivo `Dockerfile` na raiz do projeto com o conteúdo:

```Dockerfile
FROM jboss/wildfly:36.0.1.Final

COPY target/controle-autorizacao-procedimento-medico.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
```

#### C. Rodar tudo

```bash
docker-compose up --build
```

Acesse a aplicação em:

```
http://localhost:8080/
```

---

## 🛠 Banco de Dados

### 🔧 Estrutura criada via Liquibase

Arquivo principal do changelog:

```
src/main/resources/db/changelog/db.changelog-master.xml
```

Para aplicar as migrations:

```bash
mvn liquibase:update
```

Ou gerar SQL:

```bash
mvn liquibase:updateSQL
```

### 🗃️ Credenciais do PostgreSQL (padrão Docker Compose)

| Parâmetro | Valor                      |
| --------- | -------------------------- |
| Host      | localhost                  |
| Porta     | 5432                       |
| Usuário   | seu_usuario_aqui           |
| Senha     | sua_senha_aqui             |
| Banco     | autorizador\_procedimentos |

---

## 🔄 Funcionalidades

* Cadastro de novas regras de autorização de procedimentos médicos
* Solicitação de autorização de procedimento
* Validação e persistência da solicitação, com resposta via modal
* Listagem do histórico de solicitações

---

## 📍 Endpoints (Front-end)

| Caminho                                     | Função                                             |  
| ------------------------------------------- | -------------------------------------------------- |
| `/index.jsp`                             | Tela inicial                                       |
| `/pages/solicitarAutorizacao.jsp`  | Formulário de solicitação de autorização           |
| `/pages/historicoSolicitacoes.jsp` | Listagem do histórico de solicitações              |
| `/pages/cadastrarNovaRegra.jsp`     | Cadastro de novas regras de autorização            |

---

## 🧪 Testes Unitários

```bash
mvn test
```

Frameworks usados:

* JUnit 5

---

## 📁 Estrutura esperada no banco

```sql
CREATE TABLE procedimentos_autorizados (...);
CREATE TABLE solicitacoes_autorizacao (...);
```

Essas tabelas são criadas automaticamente via Liquibase.

---

## ✅ Contato

Caso tenha alguma dúvida, pode entrar em contato comigo:

<a href = "mailto:anajuliafv88@gmail.com"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"></a> 
<a href="https://www.linkedin.com/in/ajuliafernandesv/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

<p align="center"> Desenvolvido por Ana Júlia Fernandes Venâncio </
