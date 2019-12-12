package guru.springframework.business.service;

import guru.springframework.business.model.entity.UnitOfMeasure;
import guru.springframework.business.model.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UomServiceImpl implements UomService {
    UnitOfMeasureRepository unitOfMeasureRepository;

    public UomServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set <UnitOfMeasure> findAll() {
        Set <UnitOfMeasure> uomList = new HashSet <>();
        unitOfMeasureRepository.findAll().forEach(uomList::add);
        return uomList;
    }

    @Override
    public Optional<UnitOfMeasure> findById(Long id) {
        return unitOfMeasureRepository.findById(id);
    }


}
