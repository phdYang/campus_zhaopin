package top.yzhao.campus_zhaopin.model;

import lombok.Data;

import java.util.Date;

@Data
public class DetailZhaopinData {

    //详情id
    private Integer detailId;

    //投递人
    private String userName;

    //投递的公司id
    private Integer companyId;

    //投递的岗位
    private String jobName;

    //投递的部门
    private String departmentName;

    // 简历状态如：简历筛选/测评/笔试/面试/offer/凉凉
    private String status;

    //创建时间
    private String createTime;

    //修改时间
    private String updateTime;

    // 是否有效
    private Integer isValid;
}
