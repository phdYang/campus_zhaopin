package top.yzhao.campus_zhaopin.service;

import top.yzhao.campus_zhaopin.dto.DetailZhaopinDataDTO;
import top.yzhao.campus_zhaopin.model.DetailZhaopinData;

import java.util.List;

public interface DetailZhaopinDataService {

    public List<DetailZhaopinDataDTO> getAllDetailZhaopinData(Integer companyId);

    public int addOneDetailZhaopinData(DetailZhaopinData detailZhaopinData);

    public int delDetailZhaopinData(Integer detailId);

    public int editDetailZhaopinData(DetailZhaopinDataDTO detailZhaopinDataDTO);
}
