package com.dzq.example.mapper;

import com.dzq.example.entity.Version;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);
}