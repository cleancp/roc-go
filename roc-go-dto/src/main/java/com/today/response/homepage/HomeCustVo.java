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
 * @Description 机构命中图表
 * @createTime 2020年02月25日 15:35*
 * log.info()
 */
@ApiModel(value = "机构命中图表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeCustVo implements Serializable {

    /**
     * 机构命中排名：根据用户在第一张图中选中的关键词组，
     * 去统计对应命中的机构，从大到小排序，只展示前十个，横坐标为机构，纵坐标为汇总数
     */

    @ApiModelProperty(value = "机构-分公司名称")
    private String name;

    @ApiModelProperty(value = "机构-分公司命中次数")
    private Long num;
}
