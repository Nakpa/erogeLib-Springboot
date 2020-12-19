package com.library.eroge.erogelib.service.tmuser;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csvw.cloud.dms.framework.exception.ServiceBizException;
import com.csvw.cloud.dms.framework.utils.BeanMapperUtil;
import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.entity.Md5PO;
import com.library.eroge.erogelib.entity.TmUserMd5PO;
import com.library.eroge.erogelib.entity.TmUserPO;
import com.library.eroge.erogelib.mapper.Md5Mapper;
import com.library.eroge.erogelib.mapper.TmUserMapper;
import com.library.eroge.erogelib.mapper.TmUserMd5Mapper;
import com.library.eroge.erogelib.service.PasswordUtil;
import com.library.eroge.erogelib.service.operatelog.TtOperateLogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TmUserServiceImpl implements TmUserService {

    @Autowired
    private Md5Mapper md5Mapper;

    @Autowired
    private TmUserMapper tmUserMapper;

    @Autowired
    private TmUserMd5Mapper tmUserMd5Mapper;

    @Autowired
    private TtOperateLogService ttOperateLogService;

    @Override
    public List<TmUserPO> queryUserList(TmUserPO tmUserPO) {
        Map<String,Object> params = BeanMapperUtil.toMap(tmUserPO);
        List<TmUserPO> list = tmUserMapper.queryUserList(params);
        return list;
    }

    @Override
    public void insertTmUser(TmUserPO useTemp) {
        TmUserPO tmUserPO = new TmUserPO();
        tmUserPO.setUserId(UUID.randomUUID().toString());
        tmUserPO.setUserAccount(useTemp.getUserAccount());
        tmUserPO.setUserName(useTemp.getUserName());
        String password = useTemp.getAccoPassword();
        // 加密
        String passwordAfter = encrypt(password);
        tmUserPO.setAccoPassword(passwordAfter);
        tmUserPO.setEmail(useTemp.getEmail());
        tmUserPO.setTelPhone(useTemp.getTelPhone());
        tmUserPO.setRemark(useTemp.getRemark());
        tmUserPO.setCreateDate(new Date());
        tmUserMapper.insert(tmUserPO);

        // 将本次密码以及md5的值存到TM_USER_MD5内
        insertUserMd5(tmUserPO.getUserId());
    }

    @Override
    public void changePassword(UserInfoDTO userTemp) {
        String userId = userTemp.getUserId();
        TmUserMd5PO tmUserMd5PO = tmUserMd5Mapper.selectByUserId(userId);
        String oldPassword = userTemp.getOldPassword(); // 用户输入的明文密码
        TmUserPO tmUserPO = tmUserMapper.selectPassword(userId);
        // 数据库还原的原密码
        String confirmPassword = decrypt(tmUserPO.getAccoPassword() , tmUserMd5PO.getMd5());

        if(!confirmPassword.equals(oldPassword)) {
            throw new ServiceBizException("输入的原密码与账号当前密码不匹配!");
        }

        // 密码相同，开始修改密码 // 将新密码加密
        String newPassword = encrypt(userTemp.getPassword());
        LambdaQueryWrapper<TmUserPO> query = new QueryWrapper<TmUserPO>().lambda();
        query.eq(TmUserPO::getUserId , userId);
        TmUserPO newUserPO = new TmUserPO();
        newUserPO.setAccoPassword(newPassword);
        newUserPO.setUpdateDate(new Date());
        tmUserMapper.update(newUserPO , query);

        // 重新计入TM_USER_MD5
        updateUserMd5(tmUserMd5PO.getIndexId() , userId);

        // 记入日志表内
        String content = "用户"+tmUserPO.getUserName() +
                "将密码【"+oldPassword+"】修改为【"+userTemp.getPassword()+"】。";
        ttOperateLogService.insertOperateLog(10021001,content , userId);
    }

    public void insertUserMd5(String userId){
        Md5PO md5PO = md5Mapper.selectLastValidMd5();
        String md5 = md5PO.getMd5();
        TmUserMd5PO tmUserMd5PO = new TmUserMd5PO();
        tmUserMd5PO.setMd5(md5);
        tmUserMd5PO.setUserId(userId);
        tmUserMd5PO.setCreateDate(new Date());
        tmUserMd5PO.setUpdateDate(new Date());
        tmUserMd5Mapper.insert(tmUserMd5PO);
    }

    public void updateUserMd5(Integer indexId,String userId){
        LambdaQueryWrapper<TmUserMd5PO> query = new QueryWrapper<TmUserMd5PO>().lambda();
        query.eq(TmUserMd5PO::getIndexId , indexId)
                .eq(TmUserMd5PO::getUserId , userId);

        Md5PO md5PO = md5Mapper.selectLastValidMd5();
        String md5 = md5PO.getMd5();

        TmUserMd5PO tmUserMd5PO = new TmUserMd5PO();
        tmUserMd5PO.setMd5(md5);
        tmUserMd5PO.setUpdateDate(new Date());
        tmUserMd5Mapper.update(tmUserMd5PO, query);
    }

    // 加密
    private String encrypt(String password) {
        Md5PO md5PO = md5Mapper.selectLastValidMd5();
        String md5 = md5PO.getMd5();
        String passwordAfter = "";
        try {
            PasswordUtil passwordUtil = new PasswordUtil(md5);

            System.out.println("加密前的字符：" + password);

            passwordAfter = passwordUtil.encrypt(password);
            System.out.println("加密后的字符：" + passwordAfter);
//            System.out.println("解密后的字符：" + passwordUtil.decrypt(passwordAfter));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return passwordAfter;
    }

    // 解密
    private String decrypt(String oldPassword , String md5) {
//        Md5PO md5PO = md5Mapper.selectLastValidMd5();
//        String md5 = md5PO.getMd5();
        String passwordAfter = "";
        try {
            PasswordUtil passwordUtil = new PasswordUtil(md5);
            passwordAfter = passwordUtil.decrypt(oldPassword);
            System.out.println("解密后的字符：" + passwordAfter);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return passwordAfter;
    }
}
