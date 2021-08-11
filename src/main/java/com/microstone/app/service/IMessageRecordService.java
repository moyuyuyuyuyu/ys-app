package com.microstone.app.service;

import com.microstone.app.entity.Device;
import com.microstone.app.entity.MessageRecord;
import org.microstone.core.mp.base.BaseService;

import java.util.List;

public interface IMessageRecordService  extends BaseService<MessageRecord> {


    void pushMessage(String message, String title, List<Long> userId);
}
