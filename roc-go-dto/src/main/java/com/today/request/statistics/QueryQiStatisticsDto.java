package com.today.request.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
 * @createTime 2020年04月09日 13:39*
 * log.info()
 */
@ApiModel("质检统计Dto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryQiStatisticsDto extends CommonStatisticsDto implements Serializable {

    @ApiModelProperty(name = "sequence", value = "排序方式 DESC/ASC")
    private String sequence;

    @ApiModelProperty(name = "sortField", value = "排序字段 commitOrgan")
    private String sortField;
}
