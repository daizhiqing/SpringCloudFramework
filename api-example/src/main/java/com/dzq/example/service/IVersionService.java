package com.dzq.example.service;

import com.dzq.example.entity.Version;

/**
 * @Project : SpringCloudFramework
 * @Package Name : com.dzq.example.service
 * @Description : TODO
 * @Author : daizhiqing@xiaochong.com
 * @Creation Date : 2018年03月01日上午11:14
 * @ModificationHistory Who When What
 */
public interface IVersionService {
    Version findVersionById(Integer id);
}
