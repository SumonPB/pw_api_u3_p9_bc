package uce.edu.web.api.matricula.interfaces;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.UserService;
import uce.edu.web.api.matricula.application.representation.LoginRequest;
import uce.edu.web.api.matricula.application.representation.UserRepresentation;

//clase recurso usuario para exponer los servicios REST y validar en la api AUTH
//se envia un login request con username y password para validar las credenciales en la base de datos
@Path("/usuarios")
public class UserResource {
    @Inject
    UserService userService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response guardar(UserRepresentation userRepresentation) {
        this.userService.crear(userRepresentation);
        return Response.status(Response.Status.CREATED).entity(userRepresentation).build();
    }

    @POST
    @Path("validar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response validar(LoginRequest request) {

        boolean ok = userService.validarCredenciales(
                request.username,
                request.password);

        return Response.ok(ok).build();
    }

}
