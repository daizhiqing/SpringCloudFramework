package com.dzq.example.service;

import com.dzq.example.entity.Version;
import com.dzq.example.mapper.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project : SpringCloudFramework
 * @Package Name : com.dzq.example.service
 * @Description : TODO
 * @Author : daizhiqing@xiaochong.com
 * @Creation Date : 2018年02月28日下午4:15
 * @ModificationHistory Who When What
 */
@Service
public class VersionService {

    @Autowired
    VersionMapper versionMapper;

    public Version findVersionById(Integer id){
        return  versionMapper.selectByPrimaryKey(id);
    }
}
