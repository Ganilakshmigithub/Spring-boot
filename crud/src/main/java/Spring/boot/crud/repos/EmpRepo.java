package Spring.boot.crud.repos;
import Spring.boot.crud.dto.EmployeeDTO;
import Spring.boot.crud.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpRepo extends JpaRepository<employees,Integer>{

    employees save(EmployeeDTO emp);
    employees findByDesignation(String designation);

}
    

