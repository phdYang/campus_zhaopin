package top.yzhao.campus_zhaopin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yzhao.campus_zhaopin.DAO.CampusZhaopinDataDAO;
import top.yzhao.campus_zhaopin.DAO.DetailZhaopinDataDAO;
import top.yzhao.campus_zhaopin.dto.DetailZhaopinDataDTO;
import top.yzhao.campus_zhaopin.model.DetailZhaopinData;
import top.yzhao.campus_zhaopin.service.DetailZhaopinDataService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailZhaopinDataServiceImpl implements DetailZhaopinDataService {

    @Autowired
    private DetailZhaopinDataDAO repository;

    @Autowired
    private CampusZhaopinDataDAO campusZhaopinDataDAO;

    @Override
    public List<DetailZhaopinDataDTO> getAllDetailZhaopinData(Integer companyId) {
        List<DetailZhaopinData> list = repository.getAllDetailZhaopinData(companyId);
        List<DetailZhaopinDataDTO> rsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DetailZhaopinDataDTO dto = new DetailZhaopinDataDTO();
            dto.setDetailId(list.get(i).getDetailId());
            dto.setCompanyId(companyId);
            dto.setCompanyName(campusZhaopinDataDAO.getOneCampusZhaopinData(list.get(i).getCompanyId()).getCompanyName());
            dto.setJobName(list.get(i).getJobName());
            dto.setUserName(list.get(i).getUserName());
            dto.setDepartmentName(list.get(i).getDepartmentName());
            dto.setStatus(list.get(i).getStatus());
            dto.setCreateTime(list.get(i).getCreateTime());
            rsList.add(dto);
        }
        return rsList;
    }

    @Override
    public int addOneDetailZhaopinData(DetailZhaopinData detailZhaopinData) {
        return repository.addOneDetailZhaopinData(detailZhaopinData);
    }

    @Override
    public int editDetailZhaopinData(DetailZhaopinDataDTO detailZhaopinDataDTO){
        return repository.editDetailZhaopinData(detailZhaopinDataDTO);
    }

    @Override
    public int delDetailZhaopinData(Integer detailId) {
        return repository.delDetailZhaopinData(detailId);
    }
}
