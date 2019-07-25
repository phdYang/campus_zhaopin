package top.yzhao.campus_zhaopin.VO;

import lombok.Data;

import java.util.List;

@Data
public class DataVO<T> {

    private List<T> data;

    private Integer total_count;
}
