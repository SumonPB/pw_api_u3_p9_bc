package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {
    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {

        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Materia estu) {
        this.materiaRepository.persist(estu);
    }

    @Transactional
    public void actualizar(Integer id, Materia materia) {
        Materia mat = this.consultarPorId(id);
        mat.setCodigo(materia.getCodigo());
        mat.setCreditos(materia.getCreditos());
        mat.setNombre(materia.getNombre());
        mat.setDescripcion(materia.getDescripcion());
        mat.setFechaCreacion(materia.getFechaCreacion());
        // hibernate actualiza directamente el estudiante por dity cheking
    }

    @Transactional
    public void actualizarParcial(Integer id, Materia materia) {
        Materia mat = this.consultarPorId(id);
        if (materia.getCodigo() != null) {
            mat.setCodigo(materia.getCodigo());
        }
        if (materia.getCreditos() != null) {
            mat.setCreditos(materia.getCreditos());
        }
        if (materia.getNombre() != null) {
            mat.setNombre(materia.getNombre());
        }
        if (materia.getDescripcion() != null) {
            mat.setDescripcion(materia.getDescripcion());
        }
        if (materia.getFechaCreacion() != null) {
            mat.setFechaCreacion(materia.getFechaCreacion());
        }
        // hibernate actualiza directamente el estudiante por dity cheking
    }

    @Transactional
    public void eliminar(Integer id) {
        materiaRepository.deleteById(id.longValue());
    }
}
