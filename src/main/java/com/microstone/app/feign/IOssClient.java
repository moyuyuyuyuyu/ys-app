package com.microstone.app.feign;



import org.microstone.core.launch.constant.AppConstant;
import org.microstone.core.oss.model.MsFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME
)
public interface IOssClient {

    String API_PREFIX = "/client";

    String PUT_FILE = API_PREFIX + "/put-file";

    String TEST = API_PREFIX + "test";

    @PostMapping(value = PUT_FILE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    MsFile putFile(@RequestPart("file") MultipartFile file) throws IOException;

    @GetMapping(TEST)
    String test(@RequestParam("hello") String hello);

}
