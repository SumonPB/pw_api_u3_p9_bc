package uce.edu.web.api.matricula.infraestructure;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Estudiante;

@ApplicationScoped
public class EstudianteRepository implements PanacheRepository<Estudiante> {

    public List<Estudiante> buscarPorNombre(String nombre) {
        return list("nombre", nombre);
    }

    public List<Estudiante> buscarPorInicial(String letra) {
        return list(
                "upper(apellido) like :patron order by apellido",
                Parameters.with("patron", letra.toUpperCase() + "%"));
    }

}
