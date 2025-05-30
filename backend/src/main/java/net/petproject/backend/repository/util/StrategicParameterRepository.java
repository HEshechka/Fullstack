package net.petproject.backend.repository.util;

import net.petproject.backend.model.product.StrategicParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StrategicParameterRepository extends JpaRepository<StrategicParameter, Long> {
    Optional<StrategicParameter> findByParameterName(String parameterName);
}
