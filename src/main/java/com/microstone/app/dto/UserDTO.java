package com.microstone.app.dto;

import com.microstone.app.entity.User;
import lombok.Data;

@Data
public class UserDTO extends User {

    private Integer customerCount;

    private Integer focusCustomerCount;

    private Integer dealCustomerCount;

    private Integer newCustomerCount;
}
