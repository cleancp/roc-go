package com.today.roc.go.dto.request.statistics;

import com.today.roc.go.dto.response.statistics.QueryNegativeStatisticsVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年04月10日 13:39*
 * log.info()
 */
@ApiModel("导出反向质检报表Dto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportNegativeStatisticsDto implements Serializable {

    @ApiModelProperty("导出类型 导出全部 0 导出勾选 1")
    private String exportType;

    private List<QueryNegativeStatisticsVo> queryNegativeStatisticsVos;

    private QueryNegativeStatisticsDto queryNegativeStatisticsDto;

}
