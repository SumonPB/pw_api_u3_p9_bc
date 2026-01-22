package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {

        return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPorId(Integer id) {
        return this.estudianteRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Estudiante estu) {
        this.estudianteRepository.persist(estu);
    }

    @Transactional
    public void actualizar(Integer id, Estudiante estudiante) {
        Estudiante est = this.consultarPorId(id);
        est.setApellido(estudiante.getApellido());
        est.setNombre(estudiante.getNombre());
        est.setFechaNacimiento(estudiante.getFechaNacimiento());
        // hibernate actualiza directamente el estudiante por dity cheking
    }

    @Transactional
    public void actualizarParcial(Integer id, Estudiante estudiante) {
        Estudiante est = this.consultarPorId(id);
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

    public List<Estudiante> buscarPorNombre(String nombre) {
        return estudianteRepository.buscarPorNombre(nombre);
    }

    public List<Estudiante> buscarPorInicial(String letra) {
        return estudianteRepository.buscarPorInicial(letra);
    }
    //metodo panache para optimizar codigo
    public List<Estudiante> buscarPorProvincia(String provincia,String genero) {
        //return estudianteRepository.find("provincia", provincia).list();
        return estudianteRepository.find("provincia = ?1 and genero =?2",provincia,genero).list();
    }


}
