package Spring.boot.crud.controllers;

import Spring.boot.crud.services.*;
import Spring.boot.crud.dto.EmployeeDTO;
import Spring.boot.crud.dto.PhoneNumberDTO;
import Spring.boot.crud.entities.employees;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/emp")
public class EmpController {

    @Autowired
    Empservice es;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO empDTO) {
        Set<PhoneNumberDTO> phoneNumbers = empDTO.getPhoneNumbers();
        employees cemp = es.saveorUpdateEmp(empDTO, phoneNumbers);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(), "Employee data inserted successfully..", cemp));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3")int size) {
        Page<EmployeeDTO> empPage = es.getAllEmp(page, size);
    
        ApiResponse apiResponse = new ApiResponse(
            HttpStatus.OK.value(),
            "Employees retrieved for the page you entered..",
            empPage.getContent(),            
            empPage.getNumber(),             
            empPage.getTotalPages(),         
            empPage.getTotalElements()       
        );
    
        return ResponseEntity.ok().body(apiResponse);
    }

    @PutMapping("/update/{emp_id}/{Phnid}")
    public ResponseEntity<?> updatePhn(@PathVariable int emp_id, @PathVariable long Phnid, @RequestBody PhoneNumberDTO updatedPhnDTO) {
        EmployeeDTO empDTO = es.FindEmpById(emp_id);
        if (empDTO == null) {
            return ResponseEntity.notFound().build();
        }
        
        if (empDTO.getPhoneNumbers() == null || empDTO.getPhoneNumbers().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        for (PhoneNumberDTO phoneDTO : empDTO.getPhoneNumbers()) {
            if (phoneDTO.getId().equals(Phnid)) {
                phoneDTO.setPhoneNumber(updatedPhnDTO.getPhoneNumber());
                es.saveorUpdateEmp(empDTO, empDTO.getPhoneNumbers());
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(), "Phone number updated", updatedPhnDTO));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> FindEmpById(@PathVariable int id) {
        EmployeeDTO empDTO = es.FindEmpById(id);
        if (empDTO != null) {
            return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK.value(), "Employee retrieved by id", empDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/bydesignation/{designation}")
    public ResponseEntity<?> FindEmpByDesignation(@PathVariable String designation) {
        List<EmployeeDTO> empDTOList = es.FindEmpByDesignation(designation);  
        if (empDTOList != null && !empDTOList.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK.value(), "Employees retrieved by designation", empDTOList));
        } else {
            return ResponseEntity.notFound().build();  
        }
    }
    

    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteEmp(@PathVariable int id) {
        EmployeeDTO empDTO = es.FindEmpById(id);
        if (empDTO != null) {
            es.deleteEmp(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse(HttpStatus.ACCEPTED.value(), "Employee successfully deleted by id", empDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
