package com.microstone.app.feign;


import com.microstone.app.dto.CustomerDTO;
import com.microstone.app.dto.customer.CbsCustomerDTO;
import io.swagger.annotations.ApiOperation;
import org.microstone.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(value = "ms-customer")
public interface ICustomerClient {

    String API_PREFIX = "/client";
    String ADD_CLUES_CUSTOMER = API_PREFIX + "add-clues-customer";
    String SYNC_ALL_CUSTOMER = API_PREFIX + "/sync-all-customer";


    @PostMapping(ADD_CLUES_CUSTOMER)
    @ApiOperation("新增线索客户")
    R<Boolean> addCluesCustomer(@RequestBody CbsCustomerDTO customerDTO) throws Exception;



    @PostMapping(SYNC_ALL_CUSTOMER)
    R<List<CbsCustomerDTO>> syncAllCustomer();
}
