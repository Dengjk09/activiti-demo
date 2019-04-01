package com.dengjk.activitidemo.holiday;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dengjk
 * @create 2019-03-11 23:14
 * @desc 请假实体信息
 *   备注：一定要实现Serializable 接口生成serialBersionUID
 **/
@Data
@Accessors(chain = true)
public class Holiday implements Serializable{

    private static  final  long serialVersionUID =1L;

    /**请假id*/
    private Integer id;

    /**
     * 请假人名称
     */
    private String holidayName;

    /**
     * 请假开始时间
     */
    private Date startDate;

    /**
     * 请假结束时间
     */
    private Date endDate;

    /**
     * 请假天数
     */
    private Float num;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 请假类型
     */
    private String type;

}
