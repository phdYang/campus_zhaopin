package top.yzhao.campus_zhaopin.DAO;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.yzhao.campus_zhaopin.model.CampusZhaopinData;

import java.util.List;

@Repository
@Mapper
public interface CampusZhaopinDataDAO {

    // 查询所有

    @Select(" SELECT * FROM campus_zhaopin_data_2020 WHERE isValid=1 ")
    public List<CampusZhaopinData> getAllCampusZhaopinData();

    // 查询一条记录
    @Select(" SELECT * FROM campus_zhaopin_data_2020 WHERE isValid=1 and companyId=#{1}")
    public CampusZhaopinData getOneCampusZhaopinData(Integer companyId);

    // 增加一条记录
    @Insert(" INSERT INTO campus_zhaopin_data_2020 SET " +
            " companyName=#{companyName},companyType=#{companyType},zhaopinType=#{zhaopinType}, companyUrl=#{companyUrl}," +
            " startTime=#{startTime},endTime=#{endTime},exmTime=#{exmTime}, salary=#{salary}, " +
            " uploadUser=#{uploadUser},uploadIp=#{uploadIp},isValid=#{isValid} ")
    public int addOneCampusZhaopinData(CampusZhaopinData campusZhaopinData);

    // 根据id修改一条记录
    @Update(" UPDATE campus_zhaopin_data_2020 SET " +
            " companyName=#{companyName},companyType=#{companyType},zhaopinType=#{zhaopinType},companyUrl=#{companyUrl}, " +
            " startTime=#{startTime},endTime=#{endTime},exmTime=#{exmTime},uploadIp=#{uploadIp},salary=#{salary} " +
            " WHERE companyId = #{companyId} ")
    public int updateCampusZhaopinData(CampusZhaopinData campusZhaopinData) ;

    // 根据id删除一条记录
    @Update(" UPDATE campus_zhaopin_data_2020 SET isValid=0 WHERE companyId = #{1}")
    public int delCampusZhaopinData(Integer companyId);

}
