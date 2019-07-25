package top.yzhao.campus_zhaopin.service;

import top.yzhao.campus_zhaopin.model.CampusZhaopinData;

import java.util.List;

public interface CampusZhaopinDataService {

    // 查询
    public List<CampusZhaopinData> getAllCampusZhaopinData();

    public CampusZhaopinData getOneCampusZhaopinData(Integer companyId);

    // 增加
    public int addOneCampusZhaopinData(CampusZhaopinData campusZhaopinData);

    // 修改
    public int updateCampusZhaopinData(CampusZhaopinData campusZhaopinData) ;

    // 删除
    public int delCampusZhaopinData(Integer companyId);
}
