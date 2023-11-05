package au.com.telstra.simcardactivator.Repositories;

import au.com.telstra.simcardactivator.Models.Activation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ActivationsRepository extends CrudRepository<Activation, Long> {
    Optional<Activation> findByIccid(String iccid);
}
