package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.config.RedisKeyConfig;
import com.lcq.pet.common.config.SystemConfig;
import com.lcq.pet.common.third.JedisUtil;
import com.lcq.pet.common.util.TokenUtil;
import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TAppr;
import com.lcq.pet.server.dao.TApprDao;
import com.lcq.pet.server.service.intf.TApprService;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TApprServiceImpl implements TApprService{

    @Autowired
    private TApprDao tApprDao;

    @Override
    public R save(TAppr tAppr){
        if(tApprDao.insert(tAppr)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tApprDao.deleteById(id)>0){
            return R.ok();
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tApprDao.all());
    }

    @Override
    public int getApprNumByUserId(int userId) {
        return tApprDao.getApprNumByUserId(userId);
    }

    @Override
    public R makeAppr(String token, int noteId) {
        int uid= TokenUtil.getUid(token);
        System.out.println(uid);
        boolean isdel=false;//标记是否删除  之前点过赞 本次删除
        //是否存在点赞的key
        String k1= RedisKeyConfig.NOTE_APPR+noteId;
        if(JedisUtil.getInstance().ishave(k1)){
            //动态有点赞数据
            if(JedisUtil.getInstance().checkSet(k1,uid+"")){
                //当前用户点过赞--取消点赞
                JedisUtil.getInstance().delSet(k1,uid+"");
                isdel=true;
            }else{
                //当前用户没有点过赞 --点赞
                JedisUtil.getInstance().addSet(k1,uid+"");
            }
        }else{
            //动态当前没有Redis,原因：1.动态第一次点赞 2.之前失效了
            //查询Mysql-本条动态下面的所有点赞用户id
            List<Integer> list=tApprDao.getAllUserIdByApprId(noteId);
            List<String> uids=new ArrayList<>();
            if(list!=null && list.size()>0){
                for(Integer i:list){
                    if(i==uid){
                        //之前点过赞 -取消
                        isdel=true;
                        continue;
                    }else{
                        uids.add(i+"");
                    }
                }
                if(!isdel){ //之前没有点过赞 本次点赞
                    uids.add(uid+"");
                }
                String[] arr=new String[uids.size()];
                uids.toArray(arr);
                JedisUtil.getInstance().addSet(k1, arr);
            }else{
                //本条动态第一次被点赞
                JedisUtil.getInstance().addSet(k1,uid+"");
            }
            JedisUtil.getInstance().setTime(k1,RedisKeyConfig.APPR_TIME);
        }
        //同步Mysql 新增、删除
        if(isdel){
            tApprDao.deleteNoteByUserIdAndApprId(uid,noteId);
        }else {
            TAppr tAppr = new TAppr();
            tAppr.setN_id(noteId);
            tAppr.setU_id_from(uid);
            if(tApprDao.insert(tAppr)>0){
                return R.ok();
            }
        }
        return R.ok();

    }

}
