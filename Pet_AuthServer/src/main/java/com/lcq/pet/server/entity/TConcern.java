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
public class TConcern implements Serializable {
    private Integer u_id_from;
    private Integer u_id_to;
    private Integer c_flag;
}