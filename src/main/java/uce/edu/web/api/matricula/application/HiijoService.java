package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HiijoService {
    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorIdEstudiante(Integer idEstudiante) {
        List<HijoRepresentation> lista = new ArrayList<>();
        for (Hijo h : this.hijoRepository.buscarPorIdEstudiante(idEstudiante)) {
            lista.add(this.mapperToHijoR(h));
        }

        return lista;
    }

    private HijoRepresentation mapperToHijoR(Hijo hijo) {
        HijoRepresentation hr = new HijoRepresentation();
        hr.setId(hijo.getId());
        hr.setNombre(hijo.getNombre());
        hr.setApellido(hijo.getApellido());
        return hr;
    }
}
