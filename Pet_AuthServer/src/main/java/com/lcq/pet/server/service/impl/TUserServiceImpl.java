package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.config.RedisKeyConfig;
import com.lcq.pet.common.config.SystemConfig;
import com.lcq.pet.common.dto.UserDetialDto;
import com.lcq.pet.common.dto.UserDto;
import com.lcq.pet.common.dto.UserFindPass;
import com.lcq.pet.common.dto.UserUpdatePassDto;
import com.lcq.pet.common.third.JedisUtil;
import com.lcq.pet.common.util.DateUtil;
import com.lcq.pet.common.util.EncryptUtil;
import com.lcq.pet.common.util.StrUtil;
import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.util.AliSmsUtil;
import com.lcq.pet.server.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TUser;
import com.lcq.pet.server.dao.TUserDao;
import com.lcq.pet.server.service.intf.TUserService;
import org.springframework.transaction.annotation.Transactional;


/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TUserServiceImpl implements TUserService{

    @Autowired
    private TUserDao tUserDao;


    @Override
    public R save(TUser tUser){
        if(tUserDao.insert(tUser)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tUserDao.deleteById(id)>0){
            return R.ok("删除成功");
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tUserDao.all());
    }

    //检验手机号
    @Override
    public R checkPhone(String phone) {
        if(StrUtil.checkNoEmpty(phone)) {
            TUser user = tUserDao.selectByPhone(phone);
            if (user == null) {
                return R.ok("手机号可用");
            }
        }
        return R.fail("亲，该手机号已被注册，可以找回密码！");
    }


    //找回密码
    @Override
    public R findPass(UserFindPass dto) {
        if(dto!=null &&StrUtil.checkNoEmpty(dto.getU_phone())) {

            String code = JedisUtil.getInstance().getStr(RedisKeyConfig.SMS_RCODE+dto.getU_phone());
            if (code.equals(dto.getCode())) {
                dto.setU_password(EncryptUtil.aesenc(SystemConfig.PASS_KEY, dto.getU_password()));
                if (tUserDao.updatePass(dto) > 0) {
                    return R.ok("密码找回成功,请重新登录");
                }
            }
        }
        return R.fail("亲，密码找回失败，请联系客服");
    }

    //修改密码
    @Override
    public R changePass(UserUpdatePassDto dto) {
        if(dto!=null &&StrUtil.checkNoEmpty(dto.getU_phone())) {
            TUser tUser = tUserDao.selectByPhone(dto.getU_phone());
            if (EncryptUtil.aesdec(SystemConfig.PASS_KEY, tUser.getU_password()).equals(dto.getU_password())) {
                dto.setU_newpassword(EncryptUtil.aesenc(SystemConfig.PASS_KEY, dto.getU_newpassword()));
                if (tUserDao.changePass(dto)>0) {
                    return R.ok("密码修改成功");
                }
            }
        }
        return R.fail("亲，密码修改失败，请联系客服");
    }

    //发送短信验证码
    @Override
    public R code(String phone) {
        Integer c = AliSmsUtil.code(phone);
        return  R.ok(c);
    }

    //注册
    @Override
    @Transactional //开启事务
    public R registerV2(UserDto dto) {
        //1.参数校验
        if(dto!=null){
            if(StrUtil.checkNoEmpty(dto.getU_phone(),dto.getU_password())){
                //2.校验手机号时候可用
                if(tUserDao.selectByPhone(dto.getU_phone())==null) {

//                    //3.密码转换为密文同时构建用户对象
//                    TUser user = new TUser(dto.getU_phone(), EncryptUtil.aesenc(SystemConfig.PASS_KEY, dto.getU_password()));
//                    //4..校验新增是否成功
//                    if (tUserDao.insertId(user) > 0) {
//                        //5.初始化用户详情数据并且记录
//                        userdetailDao.insertInit(user.getId());
//                        tUserDao.insert(new Userlog(user.getId(), UserLogType.注册.getVal(), "新用户注册，手机号：" + dto.getPhone()));

                    String code = JedisUtil.getInstance().getStr(RedisKeyConfig.SMS_RCODE+dto.getU_phone());
                    if (code.equals(dto.getCode())){
                        TUser user = new TUser();
                        user.setU_phone(dto.getU_phone());
                        user.setU_password(EncryptUtil.aesenc(SystemConfig.PASS_KEY, dto.getU_password()));
                        if (tUserDao.insertId(user) > 0) {
                            return R.ok("注册成功");
                        }
                    }
                }
            }
        }
        return R.fail("亲，注册失败，请检查网络");
    }


    //登录
    @Override
    public R loginV2(UserDto dto) {
        //1.校验参数
        if(dto!=null){
            if(StrUtil.checkNoEmpty(dto.getU_phone(),dto.getU_password())){
                //唯一登陆 生成令牌 存储令牌
                TUser user=tUserDao.selectByPhone(dto.getU_phone());
                if(user!=null){
                    //校验账号是否有效
                    if(user.getU_flag()==1){
                        //校验密码
                        if(user.getU_password().equals(EncryptUtil.aesenc(SystemConfig.PASS_KEY,dto.getU_password()))){
                            //校验当前账号有没有在线 唯一登陆
                            if(JedisUtil.getInstance().ishave(RedisKeyConfig.AUTH_PHONE+dto.getU_phone())){
                                //存在 当前账号已在线  生成挤掉信息
                                //获取在线的令牌
                                String tk=JedisUtil.getInstance().getStr(RedisKeyConfig.AUTH_PHONE+dto.getU_phone());
                                //存储挤掉信息
                                JedisUtil.getInstance().addHash(RedisKeyConfig.AUTH_OUT,tk,"在"+ DateUtil.getTime()+"在其他地方登陆！");
                                //原来的令牌 失效
                                JedisUtil.getInstance().delKeys(RedisKeyConfig.AUTH_TOKEN+tk);
                            }
                            //生成令牌 用户id
                            String token= JwtUtil.createToken(user.getU_id()+"");
                            //存储令牌
                            //记录在线的手机号
                            JedisUtil.getInstance().addStrEx(RedisKeyConfig.AUTH_PHONE+user.getU_phone(),token,RedisKeyConfig.AUTH_TIME);

                            //记录登陆成功的令牌和对应的用户信息
                            JedisUtil.getInstance().addStrEx(RedisKeyConfig.AUTH_TOKEN+token,user.getU_id()+"",RedisKeyConfig.AUTH_TIME);
                            return R.ok(token);
                        }
                    }
                }
            }
        }
        return R.fail("亲，账号或密码不正确");
    }

    //查询个人信息
    @Override
    public TUser userdetail(String phone) {
        return tUserDao.userdetail(phone);
    }

    //修改个人信息
    @Override
    public R updateUserDetail(UserDetialDto dto) {
        if(dto!=null) {
            if (tUserDao.updateDetail(dto) > 0) {
                return R.ok("个人信息修改成功");
            }
        }
        return R.fail("亲，修改失败，请检查网络");
    }
}
