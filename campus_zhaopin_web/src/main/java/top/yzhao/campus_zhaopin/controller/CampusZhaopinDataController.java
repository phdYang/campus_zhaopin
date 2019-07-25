package top.yzhao.campus_zhaopin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yzhao.campus_zhaopin.VO.DataVO;
import top.yzhao.campus_zhaopin.VO.ObjVO;
import top.yzhao.campus_zhaopin.VO.ResultVO;
import top.yzhao.campus_zhaopin.model.CampusZhaopinData;
import top.yzhao.campus_zhaopin.service.CampusZhaopinDataService;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
public class CampusZhaopinDataController {

    @Autowired
    private CampusZhaopinDataService service;

    @Autowired
    private HttpServletRequest req;

    @PostMapping("/getAllCampusZhaopinData")
    public ResultVO<DataVO<CampusZhaopinData>> getAllCampusZhaopinData(){
        ResultVO<DataVO<CampusZhaopinData>> result = new ResultVO<>();
        result.setCode(1);
        result.setMsg("查询成功");

        DataVO<CampusZhaopinData> data = new DataVO<>();
        List<CampusZhaopinData> list = service.getAllCampusZhaopinData();
        data.setData(list);
        data.setTotal_count(list.size());

        result.setResult(data);
        return result;
    }

    @GetMapping("/getOneCampusZhaopinData")
    public ResultVO<ObjVO<CampusZhaopinData>> getOneCampusZhaopinData(Integer companyId){
        ResultVO<ObjVO<CampusZhaopinData>> result = new ResultVO<>();
        result.setCode(1);
        result.setMsg("查询成功");

        ObjVO<CampusZhaopinData> data = new ObjVO<>();
        data.setData(service.getOneCampusZhaopinData(companyId));

        result.setResult(data);
        return result;
    }

    @GetMapping("/addOneCampusZhaopinData")
    public ResultVO<ObjVO<Integer>> addOneCampusZhaopinData(CampusZhaopinData campusZhaopinData){
        //System.out.println(campusZhaopinData);
        //获得ip
        String uploadIp = req.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(uploadIp)){
            uploadIp = "127.0.0.1";
        }
        campusZhaopinData.setUploadIp(uploadIp);
        campusZhaopinData.setStartTime(campusZhaopinData.getStartTime().toString().split("T")[0]);
        campusZhaopinData.setEndTime(campusZhaopinData.getEndTime().toString().split("T")[0]);
        campusZhaopinData.setExmTime(campusZhaopinData.getExmTime().toString().split("T")[0]);
        campusZhaopinData.setIsValid(1);
        ResultVO<ObjVO<Integer>> result = new ResultVO<>();
        int data = service.addOneCampusZhaopinData(campusZhaopinData);
        //int data = -1;
        if (data>0){
            result.setCode(1);
            result.setMsg("增加成功");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }else {
            result.setCode(0);
            result.setMsg("增加失败");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }
        return result;
    }

    @GetMapping("/updateCampusZhaopinData")
    public ResultVO<ObjVO<Integer>> updateCampusZhaopinData(CampusZhaopinData campusZhaopinData){
        ResultVO<ObjVO<Integer>> result = new ResultVO<>();
        campusZhaopinData.setStartTime(campusZhaopinData.getStartTime().toString().split("T")[0]);
        campusZhaopinData.setEndTime(campusZhaopinData.getEndTime().toString().split("T")[0]);
        campusZhaopinData.setExmTime(campusZhaopinData.getExmTime().toString().split("T")[0]);
        int data = service.updateCampusZhaopinData(campusZhaopinData);
        if (data>0){
            result.setCode(1);
            result.setMsg("修改成功");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);
            result.setResult(objVO);
        }else {
            result.setCode(0);
            result.setMsg("修改失败");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);
            result.setResult(objVO);
        }
        return result;
    }

    @GetMapping("/delCampusZhaopinData")
    public ResultVO<ObjVO<Integer>> delCampusZhaopinData(Integer companyId){
        ResultVO<ObjVO<Integer>> result = new ResultVO<>();
        int data = service.delCampusZhaopinData(companyId);
        if (data>0){
            result.setCode(1);
            result.setMsg("删除成功");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }else {
            result.setCode(0);
            result.setMsg("删除失败");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }
        return result;
    }

}
