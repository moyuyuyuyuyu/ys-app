package com.microstone.app.controller;

import com.microstone.app.entity.WechatUser;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/wechatUser")
@Api(value = "", tags = "接口")
public class WechatUserController {

}
