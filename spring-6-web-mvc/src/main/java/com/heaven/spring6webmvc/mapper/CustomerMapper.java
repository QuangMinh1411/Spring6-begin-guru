package com.heaven.spring6webmvc.mapper;


import com.heaven.spring6webmvc.entities.Customer;
import com.heaven.spring6webmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDTO customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO dto);

}
