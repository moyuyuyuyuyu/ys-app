package com.microstone.app.feign;

import com.microstone.app.dto.CrmProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        value = "ms-product"
)
public interface IProductClient {


    String API_PREFIX = "/client";

    String SYNC_ALL_PRODUCT = API_PREFIX + "/sync-all-product";


    @PostMapping(SYNC_ALL_PRODUCT)
    List<CrmProductDTO> syncAllProduct();

}
