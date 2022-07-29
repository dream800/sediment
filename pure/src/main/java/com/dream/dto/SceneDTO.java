package com.dream.dto;

import com.dream.po.UserPO;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 描述信息
 *
 * @author dream
 * @create 2022-07-23
 */
@Data
public class SceneDTO {

    @Size(min = 10, message = "不能少于10")
    private String test;



    @Size(min = 5, message = "不能少于5个数据")
    private List<String> scene;


//    private List<UserPO> testList;


    

}
