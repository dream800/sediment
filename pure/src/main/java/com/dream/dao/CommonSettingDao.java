package com.dream.dao;

import com.dream.po.CommonSettingPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 描述信息
 *
 * @author dream
 * @create 2022-07-23
 */
@Repository
public interface CommonSettingDao {


    String getSvalue(String skey);

}
