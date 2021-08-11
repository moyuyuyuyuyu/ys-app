package com.microstone.app.dto;

import com.microstone.app.entity.CustomerElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerElementDTO extends CustomerElement {
    private static final long serialVersionUID = 1L;
}
