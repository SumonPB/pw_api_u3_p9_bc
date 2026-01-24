package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.MateriaRepresentation;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {
    @Inject
    private MateriaRepository materiaRepository;

    public List<MateriaRepresentation> listarTodos() {

        List<Materia> list = this.materiaRepository.listAll();
        List<MateriaRepresentation> list2 = new ArrayList<>();
        for (Materia materia : list) {
            list2.add(this.toMateriaRepresentation(materia));
        }
        return list2;
    }

    public MateriaRepresentation consultarPorId(Integer id) {
        return this.toMateriaRepresentation(this.materiaRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear(MateriaRepresentation estu) {
        this.materiaRepository.persist(this.toMateria(estu));
    }

    @Transactional
    public void actualizar(Integer id, MateriaRepresentation materia) {
        Materia mat = this.materiaRepository.findById(id.longValue());
        mat.setCodigo(materia.getCodigo());
        mat.setCreditos(materia.getCreditos());
        mat.setNombre(materia.getNombre());
        mat.setDescripcion(materia.getDescripcion());
        mat.setFechaCreacion(materia.getFechaCreacion());
        // hibernate actualiza directamente el estudiante por dity cheking
    }

    @Transactional
    public void actualizarParcial(Integer id, MateriaRepresentation materia) {
        Materia mat = this.materiaRepository.findById(id.longValue());
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

    // **************************************** */
    private Materia toMateria(MateriaRepresentation materiaRepresentation) {
        Materia materia = new Materia();
        materia.setCodigo(materiaRepresentation.getCodigo());
        materia.setCreditos(materiaRepresentation.getCreditos());
        materia.setDescripcion(materiaRepresentation.getDescripcion());
        materia.setFechaCreacion(materiaRepresentation.getFechaCreacion());
        materia.setNombre(materiaRepresentation.getNombre());
        materia.setId(materiaRepresentation.getId());
        return materia;
    }

    private MateriaRepresentation toMateriaRepresentation(Materia materia) {
        MateriaRepresentation representation = new MateriaRepresentation();
        representation.setId(materia.getId());
        representation.setCodigo(materia.getCodigo());
        representation.setCreditos(materia.getCreditos());
        representation.setDescripcion(materia.getDescripcion());
        representation.setFechaCreacion(materia.getFechaCreacion());
        representation.setNombre(materia.getNombre());
        return representation;
    }

}
