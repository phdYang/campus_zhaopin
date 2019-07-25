package top.yzhao.campus_zhaopin.VO;

import lombok.Data;

@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T result;

}
