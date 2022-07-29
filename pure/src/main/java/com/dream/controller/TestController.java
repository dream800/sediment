package com.dream.controller;

import com.dream.dao.CommonSettingDao;
import com.dream.dto.SceneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 描述信息
 *
 * @author dream
 * @create 2022-07-23
 */
@RestController
@RequestMapping("/test")
@Validated
public class TestController {

    public static final List<String> sceneList = new ArrayList<String>() {
        {add("report_manager_list");}
        {add("sensitive_manager_list");}
    };

    @Autowired
    CommonSettingDao commonSettingDao;

    @PostMapping("a")
    public HashMap<String, Boolean> testA(@RequestBody @Valid SceneDTO sceneDTO)
    {
        List<String> scenes = sceneDTO.getScene();
        HashMap<String, Boolean> sceneMap = new HashMap<>();

        for (String scene : scenes) {
            if (!sceneList.contains(scene)) {
                sceneMap.put(scene, false);
            } else {
                String svalue = commonSettingDao.getSvalue(scene);
                Boolean contains = Arrays.asList(svalue.split(",")).contains("10");
                sceneMap.put(scene, contains);
            }
        }

        return sceneMap;
    }
}
