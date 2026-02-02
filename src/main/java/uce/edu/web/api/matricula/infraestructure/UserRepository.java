package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    // metodo panache para buscar usuario por nombre
    public User buscarPorNombre(String username) {
        return find("username", username).firstResult();
    }
}
