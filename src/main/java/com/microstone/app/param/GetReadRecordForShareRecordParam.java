package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetReadRecordForShareRecordParam implements Serializable {
    private Long shareId;
}
