package com.microstone.app.dto;

import com.microstone.app.entity.Customer;
import com.microstone.app.entity.CustomerElement;
import com.microstone.app.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends Customer {
    private static final long serialVersionUID = 1L;

    private List<CustomerElementDTO> customerElementList;
}
