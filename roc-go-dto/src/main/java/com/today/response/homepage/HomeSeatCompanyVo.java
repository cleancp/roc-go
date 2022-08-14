package com.today.response.homepage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description 分公司命中图表
 * @createTime 2020年02月25日 15:29*
 * log.info()
 */
@ApiModel(value = "分公司命中图表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeSeatCompanyVo implements Serializable {

    @ApiModelProperty(value = "分公司名称")
    private String name;

    @ApiModelProperty(value = "分公司命中次数")
    private Long num;
}
