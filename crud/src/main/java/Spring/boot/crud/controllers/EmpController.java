package Spring.boot.crud.controllers;

import Spring.boot.crud.services.*;
import Spring.boot.crud.entities.*;

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
    public ResponseEntity<?> createEmployee(@RequestBody employees emp) {
        Set<PhoneNumber> phoneNumbers = emp.getPhoneNumbers();
        employees cemp = es.saveorUpdateEmp(emp, phoneNumbers);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(), "Employee data inserted successfully..", cemp));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3")int size) {
        Page<employees> empPage = es.getAllEmp(page, size);
    
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
    public ResponseEntity<?> updatePhn(@PathVariable int emp_id, @PathVariable long Phnid, @RequestBody PhoneNumber updatedPhn) {
        employees emp = es.FindEmpById(emp_id);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        if (emp.getPhoneNumbers() == null || emp.getPhoneNumbers().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (PhoneNumber ps : emp.getPhoneNumbers()) {
            if (ps.getId().equals(Phnid)) {
                ps.setPhoneNumber(updatedPhn.getPhoneNumber());
                es.saveorUpdateEmp(emp, emp.getPhoneNumbers());
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(), "Phone number updated", updatedPhn));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> FindEmpById(@PathVariable int id) {
        employees emp = es.FindEmpById(id);
        if (emp != null) {
            return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK.value(), "Employee retrieved by id", emp));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteEmp(@PathVariable int id) {
        employees emp = es.FindEmpById(id);
        if (emp != null) {
            es.deleteEmp(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse(HttpStatus.ACCEPTED.value(), "Employee successfully deleted by id", emp));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}