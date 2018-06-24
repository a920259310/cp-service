package cn.ananyz.cp.service.view;

import cn.ananyz.cp.service.model.CpData;

import javax.persistence.Column;

/**
 * Created by 王晶 on 2018/6/13.
 */
public class Result{
    private String cpDate;

    private Integer cpQiHao;

    private String indexNumP;

    /**
     * 万
     */
    private Integer wan;

    /**
     * 千
     */
    private Integer qian;

    /**
     * 百
     */
    private Integer bai;

    /**
     * 十
     */
    private Integer shi;

    /**
     * 个
     */
    private Integer ge;
}
