## Desafio Técnico – Sistema de Gestão de Projetos e Demandas

### Contexto
Sua missão é desenvolver uma **API RESTful em Java com Spring Boot** para gerenciar **projetos e tarefas (demandas)** de uma empresa.  
O sistema será utilizado por um time de desenvolvimento para organizar suas entregas, acompanhar o status das tarefas e realizar análises simples.

---

## Requisitos Técnicos

### 1. Modelagem de Domínio

A modelagem pode ser modificada pelo inscrito. Porém, precisa ser justificado o motivo.

#### `Project`
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | UUID/Long | Identificador |
| `name` | String (3–100) | **Obrigatório** |
| `description` | String | Opcional |
| `startDate` | Date | Início do projeto |
| `endDate` | Date | Opcional |

#### `Task`
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | UUID/Long | Identificador |
| `title` | String (5–150) | **Obrigatório** |
| `description` | String | Detalhes da tarefa |
| `status` | Enum | TODO / DOING / DONE |
| `priority` | Enum | LOW / MEDIUM / HIGH |
| `dueDate` | Date | Data limite |
| `projectId` | FK(Project) | Relacionamento |

---

### 2. Endpoints REST

| Método | Endpoint | Descrição |
|---------|-----------|-----------|
| **POST** | `/projects` | Criar novo projeto (`name` obrigatório) |
| **GET** | `/projects` | Listar todos os projetos (paginação opcional) |
| **POST** | `/tasks` | Criar nova tarefa vinculada a um projeto |
| **GET** | `/tasks?status=&priority=&projectId=` | Buscar tarefas com filtros opcionais |
| **PUT** | `/tasks/{id}/status` | Atualizar apenas o status da tarefa |
| **DELETE** | `/tasks/{id}` | Remover tarefa |

---

## Requisitos Obrigatórios
- **Java 17+** e **Spring Boot 3+**  
- **Spring Data JPA**  
- Banco Relacional (**PostgreSQL** ou **H2**)  
- **Bean Validation**  
- **Testes Automatizados**  
  - Unitários (Services mockados)  
  - Integração (Controllers com MockMvc ou Testcontainers)  
- Tratamento de erros com `@ControllerAdvice`  
- Uso de **DTOs** (`record` ou classes simples)  
- **README** explicando como rodar o projeto

---

## Diferenciais (Pontos Extras)
- Documentação **Swagger / OpenAPI**  
- Autenticação simples com **JWT** ou Basic Auth  
- Configuração de **Docker** / **docker-compose**  
- Uso de **MapStruct** para mapeamento de DTOs  
- Testes de API com **RestAssured**

---

## Tags
`#Java` `#SpringBoot` `#Backend` `#DesafioTecnico`  
`#API` `#RestAPI` `#Docker` `#Kubernetes`  
`#PostgreSQL` `#Oracle` `#JPA` `#Swagger`  
`#RestAssured` `#CleanCode` `#SoftwareEngineering`

---

### Dica
> Foque em **organização, boas práticas e clareza do código**.  
> Um bom README e commits bem descritos também serão avaliados.

---

## Como Executar o Projeto

### Pré-requisitos
- Java 17+
- Maven 3.6+

### Executando a Aplicação

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd dev.matheuslf.desafio.inscritos
   ```

2. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

3. **Acesse a aplicação**
   - API: `http://localhost:8080`
   - Console H2: `http://localhost:8080/h2`
     - JDBC URL: `jdbc:h2:mem:inscritosdb`
     - Username: `sa`
     - Password: (vazio)

### Variáveis de Ambiente
- `SPRING_DATASOURCE_URL` - URL do banco de dados
- `SPRING_DATASOURCE_USERNAME` - Usuário do banco de dados
- `SPRING_DATASOURCE_PASSWORD` - Senha do banco de dados
- `SPRING_DATASOURCE_DRIVER_CLASS_NAME` - Driver do banco de dados
- `SPRING_JPA_HIBERNATE_DDL_AUTO` - Auto da JPA
- `SPRING_JPA_DATABASE_PLATFORM` - Plataforma do banco de dados
- `SPRING_H2_CONSOLE_ENABLED` - Habilita o console H2
- `SPRING_H2_CONSOLE_PATH` - Caminho do console H2
- `SPRING_APPLICATION_NAME` - Nome da aplicação

### Executando os Testes

```bash
mvn test
```

### Endpoints Disponíveis

#### Projetos
- `POST /projects` - Criar novo projeto
- `GET /projects` - Listar todos os projetos (com paginação opcional)
- `GET /projects/{id}` - Buscar projeto por ID

#### Tarefas
- `POST /tasks` - Criar nova tarefa
- `GET /tasks` - Listar tarefas com filtros opcionais (status, priority, projectId)
- `PUT /tasks/{id}/status` - Atualizar status da tarefa
- `DELETE /tasks/{id}` - Remover tarefa
- `GET /tasks/{id}` - Buscar tarefa por ID

### Exemplos de Uso

#### Criar um Projeto
```bash
curl -X POST http://localhost:8080/projects \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Projeto Exemplo",
    "description": "Descrição do projeto",
    "startDate": "2024-01-01",
    "endDate": "2024-12-31"
  }'
```

#### Criar uma Tarefa
```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Implementar funcionalidade X",
    "description": "Descrição da tarefa",
    "status": "TODO",
    "priority": "HIGH",
    "dueDate": "2024-02-15",
    "projectId": "uuid-do-projeto"
  }'
```

#### Atualizar Status da Tarefa
```bash
curl -X PUT http://localhost:8080/tasks/{id}/status \
  -H "Content-Type: application/json" \
  -d '{
    "status": "DOING"
  }'
```

### Arquitetura

O projeto segue os princípios da **Domain-Driven Design (DDD)** e **Clean Architecture** com as seguintes camadas:

- **Domain**: Entidades, repositórios, serviços de domínio e eventos de domínio
- **Application**: Application Services que orquestram casos de uso
- **Interfaces**: Controllers e DTOs
- **Infrastructure**: Configurações, adapters e event handlers

#### Padrões DDD Implementados:

- **Aggregate Root**: `Project` é um aggregate root que contém `Task`
- **Domain Events**: Eventos para comunicação entre bounded contexts
- **Specifications**: Padrão para consultas complexas
- **Application Services**: Orquestração de casos de uso
- **Repository Pattern**: Abstração da persistência de dados

### Funcionalidades Implementadas

- Modelagem completa de domínio (Project e Task) seguindo DDD
- Endpoints REST conforme especificação
- Validação com Bean Validation
- Tratamento de erros global
- Testes unitários e de integração
- Paginação opcional
- Filtros de busca para tarefas usando Specifications
- Domain Events para comunicação entre bounded contexts
- Application Services para orquestração de casos de uso
- Aggregate Root com métodos de domínio
- Banco H2 em memória para desenvolvimento
- Configuração de variáveis de ambiente com Dotenv
- Documentação com OpenAPI

### Implementações Futuras
- Autenticação Básica
- Configuração de banco de dados PostgreSQL 
- Config do docker-compose para o banco de dados PostgreSQL e a aplicação
- Migrations com Flyway


### Licença
Este projeto foi desenvolvido exclusivamente para o **processo seletivo SIS Innov & Tech** e não deve ser utilizado para fins comerciais.

---
