package uce.edu.web.api.matricula.interfaces;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HiijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;

@Path("/estudiantes")

public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HiijoService hiijoService;

    @Context
    private UriInfo uriInfo; // obtener la url con la que se trabaja de manera dinamica

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin") // permisos para acceder al usuario
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("LISTAR TODOS XXXXX");
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (EstudianteRepresentation er : estudianteService.listarTodos()) {
            this.construirLinks(er);
            list.add(er);
        }
        return list;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON) // lo que se esta produciendo
    // @PermitAll ->permitir todos
    @RolesAllowed("admin")
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.construirLinks(this.estudianteService.consultarPorId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardar(EstudianteRepresentation estudianteRepresentation) {
        this.estudianteService.crear(estudianteRepresentation);
        return Response.status(Response.Status.CREATED).entity(estudianteRepresentation).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation estudianteRepresentation) {
        this.estudianteService.actualizar(id, estudianteRepresentation);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation estudianteRepresentation) {
        this.estudianteService.actualizarParcial(id, estudianteRepresentation);
        return Response.status(209).entity(estudianteRepresentation).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void borrar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    // *********************************************************************** */
    @GET
    @Path("/nombre")
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> consultarPorNombre(@QueryParam("nombre") String nombre) {
        return this.estudianteService.buscarPorNombre(nombre);
    }

    @GET
    @Path("/nombre/inicial")
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> consultarPorInicial(@QueryParam("inicial") String inicial) {
        return this.estudianteService.buscarPorInicial(inicial);
    }

    // ******************************************************** */
    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("LISTAR TODOS POR PROVINCIA Y GENEROS XXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    // *********************************** */
    @GET
    @Path("/{id}/hijos")
    @RolesAllowed("admin")
    public List<HijoRepresentation> buscarPorIdEstudiante(@PathParam("id") Integer id) {
        return this.hiijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er) {
        String self = this.uriInfo.getBaseUriBuilder()
                .path(EstudianteResource.class)
                .path(String.valueOf(er.getId()))
                .build().toString(); // obtener la url de base

        // construccion urls hijos
        String hijos = this.uriInfo.getBaseUriBuilder()
                .path(EstudianteResource.class)
                .path(String.valueOf(er.getId()))
                .path("hijos").build().toString();

        er.setLink(List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos"))); // enviar la url y el string selg
        return er;
    }
}
