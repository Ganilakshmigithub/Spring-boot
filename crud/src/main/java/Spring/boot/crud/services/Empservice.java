package Spring.boot.crud.services;

import Spring.boot.crud.entities.*;
import Spring.boot.crud.dto.*;
import Spring.boot.crud.repos.EmpRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Empservice {

    @Autowired
    private EmpRepo er;

    //emp to dto
    private EmployeeDTO convertToDTO(employees employee) {
        Set<PhoneNumberDTO> phoneNumberDTOs = employee.getPhoneNumbers().stream()
            .map(phone -> new PhoneNumberDTO(phone.getId(), phone.getPhoneNumber()))
            .collect(Collectors.toSet());
        return new EmployeeDTO(employee.getId(),employee.getName(),phoneNumberDTOs,employee.getAddress(),employee.getDesignation(),employee.getSalary());
    }

    //dto to emp
    private employees convertToEntity(EmployeeDTO empDTO, Set<PhoneNumberDTO> phoneNumbersDTO) {
        employees emp = new employees();
        emp.setId(empDTO.getId());
        emp.setName(empDTO.getName());
        emp.setAddress(empDTO.getAddress());
        emp.setDesignation(empDTO.getDesignation());
        emp.setSalary(empDTO.getSalary());
        
        Set<PhoneNumber> phoneNumbers = phoneNumbersDTO.stream()
            .map(dto -> new PhoneNumber(dto.getPhoneNumber()))  //to create phone number dto
            .collect(Collectors.toSet());

        emp.setPhoneNumbers(phoneNumbers);
        return emp;
    }

    public employees saveorUpdateEmp(EmployeeDTO empDTO, Set<PhoneNumberDTO> phoneNumbersDTO) {
        employees emp = convertToEntity(empDTO, phoneNumbersDTO);
        return er.save(emp);
    }

    public Page<EmployeeDTO> getAllEmp(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<employees> empPage = er.findAll(pageable);
        Page<EmployeeDTO> dtoPage = empPage.map(this::convertToDTO);
        return dtoPage;
    }

    public EmployeeDTO FindEmpById(int id) {
        employees emp = er.findById(id).orElse(null);
        if (emp != null) {
            return convertToDTO(emp); 
        }
       return null;
       }

       public List<EmployeeDTO> FindEmpByDesignation(String designation) {
        List<employees> employeesList = er.findByDesignation(designation);
        if (employeesList != null && !employeesList.isEmpty()) {
            // Convert List of employees to List of EmployeeDTO
            return employeesList.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }
        return null;  
    }
    

    public void deleteEmp(int id) {
        er.deleteById(id);
    }
}
