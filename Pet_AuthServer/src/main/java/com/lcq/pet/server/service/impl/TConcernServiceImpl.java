package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TConcern;
import com.lcq.pet.server.dao.TConcernDao;
import com.lcq.pet.server.service.intf.TConcernService;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TConcernServiceImpl implements TConcernService{

    @Autowired
    private TConcernDao tConcernDao;

    @Override
    public R save(TConcern tConcern){
        if(tConcernDao.insert(tConcern)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tConcernDao.deleteById(id)>0){
            return R.ok();
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tConcernDao.all());
    }

    @Override
    public R concern(TConcern concern) {
        //查询该用户是否已经关注了该用户
        TConcern concern1 = tConcernDao.isConcern(concern.getU_id_from(), concern.getU_id_to());

        if (concern1 == null ){//没有关注，则直接添加关注，先判断对方是否已经关注了自己
            if (tConcernDao.isConcern( concern.getU_id_to(),concern.getU_id_from()) != null ){//对方已经关注了自己，则直接把那条记录标记改为双向关注
               return tConcernDao.makeConcernBoth(concern.getU_id_to(),concern.getU_id_from()) == 1 ?  R.ok("关注成功！") : R.fail("关注失败！");
            }
            return tConcernDao.insert(concern) > 0 ? R.ok("关注成功！") : R.fail("关注失败！");
        }else {//已经关注了，则查询是否是双向关注
            if ( concern1.getC_flag() == 1){//双向关注，改变该条记录，变成反过来的单向关注
                if (tConcernDao.makeConcernRevers(concern.getU_id_from(),concern.getU_id_to()) == 1){
                    return R.ok("取关成功！");
                }
            }else {//单向关注，直接删除该条记录
                tConcernDao.deleteById(concern.getU_id_from());
                return R.ok("取关成功！");
            }

        }
        return  null;
    }

    @Override
    public int getConcernNumByUserId(int userId) {
        return tConcernDao.getConcernNumByUserId(userId);
    }
}
