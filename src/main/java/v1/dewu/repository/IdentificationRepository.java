package v1.dewu.repository;
import org.springframework.data.repository.CrudRepository;
import v1.dewu.entity.CodeUser;
import v1.dewu.entity.Identification;

public interface IdentificationRepository extends CrudRepository<Identification, Long>
{
}
