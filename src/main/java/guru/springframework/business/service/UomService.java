package guru.springframework.business.service;

import guru.springframework.business.model.entity.UnitOfMeasure;

import java.util.Optional;
import java.util.Set;

public interface UomService {
    Set <UnitOfMeasure> findAll();
    Optional<UnitOfMeasure> findById(Long id);
}
