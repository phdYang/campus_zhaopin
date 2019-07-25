package top.yzhao.campus_zhaopin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yzhao.campus_zhaopin.VO.DataVO;
import top.yzhao.campus_zhaopin.VO.ObjVO;
import top.yzhao.campus_zhaopin.VO.ResultVO;
import top.yzhao.campus_zhaopin.dto.DetailZhaopinDataDTO;
import top.yzhao.campus_zhaopin.model.DetailZhaopinData;
import top.yzhao.campus_zhaopin.service.DetailZhaopinDataService;

import java.util.List;

@RestController
public class DetailZhaopinDataController {

    @Autowired
    private DetailZhaopinDataService service;

    @GetMapping("/getAllDetailZhaopinData")
    public ResultVO<DataVO> getAllDetailZhaopinData(Integer companyId){
        ResultVO<DataVO> result = new ResultVO<>();
        result.setCode(1);
        result.setMsg("查询成功");

        DataVO<DetailZhaopinDataDTO> data = new DataVO<>();
        List<DetailZhaopinDataDTO> list = service.getAllDetailZhaopinData(companyId);
        data.setData(list);
        data.setTotal_count(list.size());

        result.setResult(data);
        return result;
    }


    @GetMapping("/addOneDetailZhaopinData")
    public ResultVO<ObjVO<Integer>> addOneDetailZhaopinData(DetailZhaopinData detailZhaopinData){
        //System.out.println(detailZhaopinData);

        ResultVO<ObjVO<Integer>> result = new ResultVO<>();
        detailZhaopinData.setIsValid(1);
        int data = service.addOneDetailZhaopinData(detailZhaopinData);
        if (data>0){
            result.setCode(1);
            result.setMsg("投递成功");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }else {
            result.setCode(0);
            result.setMsg("投递失败");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }
        return result;
    }

    @GetMapping("/delDetailZhaopinData")
    public ResultVO<ObjVO<Integer>> delDetailZhaopinData(Integer detailId){
        ResultVO<ObjVO<Integer>> result = new ResultVO<>();
        int data = service.delDetailZhaopinData(detailId);
        if (data>0){
            result.setCode(1);
            result.setMsg("投递记录删除成功");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }else {
            result.setCode(0);
            result.setMsg("投递记录删除失败");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }
        return result;
    }

    @GetMapping("/editDetailZhaopinData")
    public ResultVO<ObjVO<Integer>> editDetailZhaopinData(DetailZhaopinDataDTO detailZhaopinDataDTO){
        ResultVO<ObjVO<Integer>> result = new ResultVO<>();
        int data = service.editDetailZhaopinData(detailZhaopinDataDTO);
        if (data>0){
            result.setCode(1);
            result.setMsg("投递记录修改成功");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }else {
            result.setCode(0);
            result.setMsg("投递记录修改失败");
            ObjVO<Integer> objVO = new ObjVO<>();
            objVO.setData(data);

            result.setResult(objVO);
        }
        return result;
    }

}
