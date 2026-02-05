package uce.edu.web.api.matricula.application;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import uce.edu.web.api.matricula.application.representation.UserRepresentation;
import uce.edu.web.api.matricula.application.representation.Token;
import uce.edu.web.api.matricula.domain.User;
import uce.edu.web.api.matricula.infraestructure.UserRepository;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public void crear(UserRepresentation ur) {
        this.userRepository.persist(this.mapperToRU(ur));
    }

    // **************************************************** */
    // metodo para buscar usuario por nombre
    public UserRepresentation findByName(String username) {
        User user = userRepository.buscarPorNombre(username);
        if (user == null) {
            return null;
        }

        return mapperToUR(user);
    }

    // metodo para validar credenciales usando bcrypt y buscando el usuario por
    // nombre
    public Token validarCredenciales(String username, String password) {
        User user = userRepository.buscarPorNombre(username);
        Token token = new Token();
        if (user == null) {
            token.setIsValid(false);
            token.setRole("none");
            return token;
        }
        token.setIsValid(BcryptUtil.matches(password, user.getPassword()));
        token.setRole(user.getRole());
        return token;
    }

    // ********************************************************************* */
    private User mapperToRU(UserRepresentation userRepresentation) {
        User user = new User();
        user.setId(userRepresentation.getId());
        user.setUsername(userRepresentation.getUsername());
        user.setPassword(BcryptUtil.bcryptHash(userRepresentation.getPassword()));
        user.setRole(userRepresentation.getRole());
        return user;

    }

    private UserRepresentation mapperToUR(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(user.getId());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setPassword(user.getPassword());
        userRepresentation.setRole(user.getRole());
        return userRepresentation;

    }

}
