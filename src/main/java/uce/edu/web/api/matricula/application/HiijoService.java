package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HiijoService {
    @Inject
    private HijoRepository hijoRepository;
    
    public List<Hijo> buscarPorIdEstudiante(Integer idEstudiante){
        return this.hijoRepository.buscarPorIdEstudiante(idEstudiante);
    }

    @Transactional
    public void crear(Hijo hijo) {
        this.hijoRepository.persist(hijo);
    }
}
