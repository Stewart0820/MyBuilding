package com.stewart.building.mbg.service.impl;

import com.stewart.building.mbg.pojo.Message;
import com.stewart.building.mbg.mapper.MessageMapper;
import com.stewart.building.mbg.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
