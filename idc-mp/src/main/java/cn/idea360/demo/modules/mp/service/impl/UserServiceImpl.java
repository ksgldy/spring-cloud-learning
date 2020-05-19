package cn.idea360.demo.modules.mp.service.impl;

import cn.idea360.demo.modules.mp.entity.User;
import cn.idea360.demo.modules.mp.mapper.UserMapper;
import cn.idea360.demo.modules.mp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 当我遇上你
 * @since 2020-05-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
