package com.lcq.pet.server.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Data
public class TComment implements Serializable {
    private Integer c_id;
    private String c_content;
    private String  c_time;
    private Integer c_userid;
    private Integer c_to_cid;
    private Integer c_to_nid;
}