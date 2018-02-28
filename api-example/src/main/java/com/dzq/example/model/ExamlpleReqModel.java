package com.dzq.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Project : SpringCloudFramework
 * @Package Name : com.dzq.example.model
 * @Description : TODO
 * @Author : daizhiqing@xiaochong.com
 * @Creation Date : 2018年02月28日下午4:18
 * @ModificationHistory Who When What
 */
@ApiModel
public class ExamlpleReqModel {

    @ApiModelProperty(value = "版本ID", name = "id", required = true)
    @Min(value = 1 , message = "哪有这么小")
    @Max(value = 10 ,message = "没有这么大哦")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
