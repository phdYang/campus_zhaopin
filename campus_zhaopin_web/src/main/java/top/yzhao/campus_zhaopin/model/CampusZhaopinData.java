package top.yzhao.campus_zhaopin.model;


import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Data
public class CampusZhaopinData {

    // 信息id
    private Integer companyId;

    // 公司名称
    private String companyName;

    // 互联网一线/互联网独角兽/银行/国企/央企
    private String companyType;

    // 实习/提前批/正式批
    private String zhaopinType;

    // 地址
    private String companyUrl;

    //简历投递开始时间
    private String startTime;

    // 简历投递结束时间
    private String endTime;

    // 笔试开始时间
    private String exmTime;

    // 上传人
    private String uploadUser;

    // 上传人ip
    private String uploadIp;

    // 上传时间
    private String uploadTime;

    // 修改时间
    private String updateTime;

    // 是否有效
    private Integer isValid;

    //
    private String salary;


}
