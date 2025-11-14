package org.blyweertboukari.soa.order_service.resources;

import org.blyweertboukari.soa.order_service.Database;
import org.blyweertboukari.soa.order_service.dto.CustomerCreationDto;
import org.blyweertboukari.soa.order_service.dto.CustomerUpdateDto;
import org.blyweertboukari.soa.order_service.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/customers")
public class CustomerResource {
    @PostMapping()
    public ResponseEntity<Customer> register(@RequestBody CustomerCreationDto customerCreationDto) {
        if (customerCreationDto.getFirstName() == null || "".equals(customerCreationDto.getFirstName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (customerCreationDto.getLastName() == null || "".equals(customerCreationDto.getLastName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer newCustomer = Database.addCustomer(customerCreationDto);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = Database.getCustomer(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable int id, @RequestBody CustomerUpdateDto customerUpdateDto) {
        Customer customer = Database.getCustomer(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (customerUpdateDto.getFirstName() == null || "".equals(customerUpdateDto.getFirstName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (customerUpdateDto.getLastName() == null || "".equals(customerUpdateDto.getLastName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer updatedCustomer = Database.updateCustomer(customer.getId(), customerUpdateDto);

        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable int id) {
        Customer customer = Database.getCustomer(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Database.deleteCustomer(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
