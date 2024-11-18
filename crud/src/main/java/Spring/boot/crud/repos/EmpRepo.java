package Spring.boot.crud.repos;
import Spring.boot.crud.dto.EmployeeDTO;
import Spring.boot.crud.entities.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpRepo extends JpaRepository<employees,Integer>{

    employees save(EmployeeDTO emp);
    List<employees> findByDesignation(String designation);

}
    

