package top.yzhao.campus_zhaopin.dto;

import lombok.Data;

@Data
public class DetailZhaopinDataDTO {


    //
    private int detailId;

    //
    private Integer companyId;

    private String companyName;
    //投递人
    private String userName;

    //投递的岗位
    private String jobName;

    //投递的部门
    private String departmentName;

    //
    private String status;

    //创建时间
    private String createTime;

}
