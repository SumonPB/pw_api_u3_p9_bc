package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.application.representation.MateriaRepresentation;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON) // listar todos
    public List<MateriaRepresentation> listarTodos() {

        return materiaService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public MateriaRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.materiaService.consultarPorId(id);
    }

    @POST
    @Path("")
    public Response guardar(MateriaRepresentation materia) {
        this.materiaService.crear(materia);
        return Response.status(Response.Status.CREATED).entity(materia).build(); // guardar
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, MateriaRepresentation materia) { // actualizar
        this.materiaService.actualizar(id, materia);
        return Response.status(Response.Status.OK).entity(materia).build();
    }

    @PATCH
    @Path("/{id}")
    public Response actualizarParcial(@PathParam("id") Integer id, MateriaRepresentation materia) { // parcial
        this.materiaService.actualizarParcial(id, materia);
        return Response.status(209).entity(materia).build();
    }

    @DELETE
    @Path("/{id}")
    public Response borrar(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
