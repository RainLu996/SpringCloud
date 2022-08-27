package com.lujun61.useradd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lujun61.useradd.dao.UserMapper;
import com.lujun61.useradd.entity.User;
import com.lujun61.useradd.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
