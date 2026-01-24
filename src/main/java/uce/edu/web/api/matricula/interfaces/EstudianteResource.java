package uce.edu.web.api.matricula.interfaces;

import java.util.List;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HiijoService;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;


@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HiijoService hiijoService;


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> listarTodos() {
        System.out.println("LISTAR TODOS XXXXX");
        return estudianteService.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)//lo que se esta produciendo
    public Estudiante consultarPorId(@PathParam("id") Integer id) {
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(Estudiante estudiante) {
        this.estudianteService.crear(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizar(id, estudiante);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarParcial(id, estudiante);
        return Response.status(209).entity(estudiante).build();
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    // *********************************************************************** */
    @GET
    @Path("/nombre")
    public List<Estudiante> consultarPorNombre(@QueryParam("nombre") String nombre) {
        return this.estudianteService.buscarPorNombre(nombre);
    }

    @GET
    @Path("/nombre/inicial")
    public List<Estudiante> consultarPorInicial(@QueryParam("inicial") String inicial) {
        return this.estudianteService.buscarPorInicial(inicial);
    }
    //******************************************************** */
    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero){
            System.out.println("LISTAR TODOS POR PROVINCIA Y GENEROS XXXXX");
        return this.estudianteService.buscarPorProvincia(provincia,genero);
    }
    //*********************************** */
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> buscarPorIdEstudiante(@PathParam("id") Integer id){
        return this.hiijoService.buscarPorIdEstudiante(id);
    }

}
