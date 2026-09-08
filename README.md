# patinha-feliz

Projeto pessoal — API REST para gerenciamento de usuários, animais e postagens de adoção.

## Stack

- Java 21, Spring Boot 3.4.x (Web, Data JPA, Validation, Actuator)
- MySQL
- springdoc-openapi (Swagger UI em `/swagger-ui.html`)

## Configuração

As credenciais do banco são lidas de variáveis de ambiente (não há segredos no repositório):

| Variável       | Padrão (dev)                                                        |
|----------------|--------------------------------------------------------------------|
| `DB_URL`       | `jdbc:mysql://localhost:3306/patinha_feliz?createDatabaseIfNotExist=true&serverTimezone=UTC` |
| `DB_USERNAME`  | `root`                                                             |
| `DB_PASSWORD`  | *(vazio)*                                                          |
| `JPA_DDL_AUTO` | `update`                                                          |
| `JPA_SHOW_SQL` | `false`                                                           |

## Como rodar

```bash
export DB_PASSWORD=sua_senha
./mvnw spring-boot:run
```

## Testes

```bash
./mvnw test
```
