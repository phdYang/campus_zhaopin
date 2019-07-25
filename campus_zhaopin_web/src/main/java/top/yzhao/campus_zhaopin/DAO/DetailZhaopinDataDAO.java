package top.yzhao.campus_zhaopin.DAO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.yzhao.campus_zhaopin.dto.DetailZhaopinDataDTO;
import top.yzhao.campus_zhaopin.model.DetailZhaopinData;

import java.util.List;

@Repository
@Mapper
public interface DetailZhaopinDataDAO {

    // 根据公司id查询
    @Select("SELECT * FROM detail_zhaopin_data_2020 WHERE isValid=1 and companyId=#{1}")
    public List<DetailZhaopinData> getAllDetailZhaopinData(Integer companyId);

    // 增加一条信息
    @Insert("INSERT INTO detail_zhaopin_data_2020 SET " +
            " userName=#{userName}, companyId=#{companyId}, jobName=#{jobName}, status=#{status},departmentName=#{departmentName},isValid=#{isValid}")
    public int addOneDetailZhaopinData(DetailZhaopinData detailZhaopinData);

    @Update("UPDATE detail_zhaopin_data_2020 SET jobName=#{jobName},departmentName=#{departmentName},status=#{status} WHERE detailId = #{detailId}")
    public int editDetailZhaopinData(DetailZhaopinDataDTO detailZhaopinDataDTO);

    // 删除一条信息
    @Update("UPDATE detail_zhaopin_data_2020 SET isValid=0 WHERE detailId = #{1}")
    public int delDetailZhaopinData(Integer detailId);
}
