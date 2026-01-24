package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        List<Estudiante> listE = this.estudianteRepository.listAll();
        for (Estudiante estudiante : listE) {
            list.add(mapperToER(estudiante));
        }
        return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToER(estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear(EstudianteRepresentation estu) {
        this.estudianteRepository.persist(this.mapperToRE(estu));
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation estudiante) {

        Estudiante est = estudianteRepository.findById(id.longValue());
        est.setApellido(estudiante.getApellido());
        est.setNombre(estudiante.getNombre());
        est.setFechaNacimiento(estudiante.getFechaNacimiento());
        // hibernate actualiza directamente el estudiante por dity cheking
    }

    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation estudiante) {
        Estudiante est = estudianteRepository.findById(id.longValue());
        if (estudiante.getNombre() != null) {
            est.setApellido(estudiante.getApellido());
        }
        if (estudiante.getApellido() != null) {
            est.setApellido(estudiante.getApellido());
        }
        if (estudiante.getFechaNacimiento() != null) {
            est.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        // hibernate actualiza directamente el estudiante por dity cheking
    }

    @Transactional
    public void eliminar(Integer id) {
        estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorNombre(String nombre) {
        List<Estudiante> list = estudianteRepository.buscarPorNombre(nombre);
        List<EstudianteRepresentation> list2 = new ArrayList<>();
        for (Estudiante estudiante : list) {
            list2.add(this.mapperToER(estudiante));
        }
        return list2;
    }

    public List<EstudianteRepresentation> buscarPorInicial(String letra) {
        List<Estudiante> list = estudianteRepository.buscarPorInicial(letra);
        List<EstudianteRepresentation> list2 = new ArrayList<>();
        for (Estudiante estudiante : list) {
            list2.add(this.mapperToER(estudiante));
        }
        return list2;
    }

    // metodo panache para optimizar codigo
    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        // return estudianteRepository.find("provincia", provincia).list();
        List<Estudiante> list = estudianteRepository.find("provincia = ?1 and genero =?2", provincia, genero).list();
        List<EstudianteRepresentation> list2 = new ArrayList<>();
        for (Estudiante estudiante : list) {
            list2.add(this.mapperToER(estudiante));
        }
        return list2;
    }

    // cast
    // ***********************************************************************************************************
    // */
    // estudiante a estudianteRepresentation
    private EstudianteRepresentation mapperToER(Estudiante estudiante) {
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.setId(estudiante.getId());
        estuR.setApellido(estudiante.getApellido());
        estuR.setFechaNacimiento(estudiante.getFechaNacimiento());
        estuR.setNombre(estudiante.getNombre());
        estuR.setGenero(estudiante.getGenero());
        estuR.setProvincia(estudiante.getProvincia());

        return estuR;
    }

    // estudianteRepresentation a estudiante
    private Estudiante mapperToRE(EstudianteRepresentation estudianteR) {
        Estudiante estu = new Estudiante();
        estu.setId(estudianteR.getId());
        estu.setApellido(estudianteR.getApellido());
        estu.setFechaNacimiento(estudianteR.getFechaNacimiento());
        estu.setNombre(estudianteR.getNombre());
        estu.setGenero(estudianteR.getGenero());
        estu.setProvincia(estudianteR.getProvincia());

        return estu;
    }
}
