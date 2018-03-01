package com.dzq.example.service.impl;

import com.dzq.example.entity.Version;
import com.dzq.example.mapper.VersionMapper;
import com.dzq.example.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
@Primary
public class VersionService implements IVersionService{

    @Autowired
    VersionMapper versionMapper;

    @Override
    public Version findVersionById(Integer id){
        return  versionMapper.selectByPrimaryKey(id);
    }
}
