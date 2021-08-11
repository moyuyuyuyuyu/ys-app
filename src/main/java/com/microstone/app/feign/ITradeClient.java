package com.microstone.app.feign;

import com.microstone.app.dto.PositionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        value = "ms-trade"
)
public interface ITradeClient {


    String API_PREFIX = "/client";
    String SYNC_ALL_POSITION = API_PREFIX + "/sync-all-position";


    @PostMapping(SYNC_ALL_POSITION)
    List<PositionDTO> syncAllPosition();
}
