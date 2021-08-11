package com.microstone.app.dto;

import com.microstone.app.entity.CustomerGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerGroupDTO extends CustomerGroup {
    private static final long serialVersionUID = 1L;
}
