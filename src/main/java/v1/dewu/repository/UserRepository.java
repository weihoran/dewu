package v1.dewu.repository;
import org.springframework.data.repository.CrudRepository;
import v1.dewu.entity.CodeUser;

public interface UserRepository extends CrudRepository<CodeUser, String>
{
}
