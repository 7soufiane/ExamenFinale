# Intégration MapStruct avec Spring Boot

- Les interfaces MapStruct sont annotées avec `@Mapper(componentModel = "spring")`.
- Aucun bean ou configuration supplémentaire n'est nécessaire.
- Injectez vos mappers dans vos services via `@Autowired` ou constructeur.

**Exemple d'injection dans un service :**
```java
@Service
public class ClientService {
    private final ClientMapper clientMapper;

    public ClientService(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }
    // ...utilisation du mapper...
}
```
