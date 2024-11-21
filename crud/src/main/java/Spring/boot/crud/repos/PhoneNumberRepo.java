package Spring.boot.crud.repos;
import org.springframework.data.jpa.repository.JpaRepository;

import Spring.boot.crud.entities.*;

public interface PhoneNumberRepo  extends JpaRepository<PhoneNumber,Long>{
    
}
