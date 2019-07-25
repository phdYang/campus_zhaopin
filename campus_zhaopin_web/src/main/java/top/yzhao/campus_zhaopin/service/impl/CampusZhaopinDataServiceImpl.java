package top.yzhao.campus_zhaopin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yzhao.campus_zhaopin.DAO.CampusZhaopinDataDAO;
import top.yzhao.campus_zhaopin.model.CampusZhaopinData;
import top.yzhao.campus_zhaopin.service.CampusZhaopinDataService;

import java.util.List;

@Service
public class CampusZhaopinDataServiceImpl implements CampusZhaopinDataService {

    @Autowired
    private CampusZhaopinDataDAO repository;

    @Override
    public List<CampusZhaopinData> getAllCampusZhaopinData() {
        return repository.getAllCampusZhaopinData();
    }

    @Override
    public CampusZhaopinData getOneCampusZhaopinData(Integer companyId){
        return repository.getOneCampusZhaopinData(companyId);
    }
    @Override
    public int addOneCampusZhaopinData(CampusZhaopinData campusZhaopinData) {
        return repository.addOneCampusZhaopinData(campusZhaopinData);
    }

    @Override
    public int updateCampusZhaopinData(CampusZhaopinData campusZhaopinData) {
        return repository.updateCampusZhaopinData(campusZhaopinData);
    }

    @Override
    public int delCampusZhaopinData(Integer companyId) {
        return repository.delCampusZhaopinData(companyId);
    }
}
