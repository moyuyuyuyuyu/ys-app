package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetUserInfoParam implements Serializable {
    private Long appUserId;
}
