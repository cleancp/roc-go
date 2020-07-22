package com.today.roc.go.dto.response.homepage;

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
 * @Description 首页关键词组图表
 * @createTime 2020年02月25日 09:10*
 * log.info()
 */
@ApiModel(value = "首页关键词组图表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyWordTeamVo implements Serializable {

    //关键词组命中排名：指根据商户设置的关键词质检规则，作为单个分组，
    // 去统计一定时间段内的汇总排序。从大到小排序，只展示前十个。横坐标为关键词组，纵坐标为汇总数

    //关键词组ID
    @ApiModelProperty(value = "关键词组ID")
    private Long id;
    //关键词组名称
    @ApiModelProperty(value = "关键词组名称")
    private String name;
    //命中数量
    @ApiModelProperty(value = "关键词组命中数量")
    private Long num;

}
