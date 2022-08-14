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
 * @Description
 * @createTime 2020年02月25日 15:26*
 * log.info()
 */
@ApiModel(value = "关键词命中图表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeKeyWordVo implements Serializable {

    /**关键词命中排名：根据用户在第一张图中选中的关键词组，
     去统计对应命中的关键词，从大到小排序，只展示前十个*/

    @ApiModelProperty(value = "关键词名称")
    private String name;

    @ApiModelProperty(value = "关键词命中次数")
    private Long num;

}
