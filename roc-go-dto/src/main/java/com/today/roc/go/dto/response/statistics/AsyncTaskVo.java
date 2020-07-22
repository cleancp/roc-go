package com.today.roc.go.dto.response.statistics;

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
 * @createTime 2019年07月30日 16:50*
 * log.info()
 */
@ApiModel(value = "异步任务返回")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsyncTaskVo implements Serializable {

    @ApiModelProperty(name = "serialNo", value = "异步流水号")
    private String serialNo;

    @ApiModelProperty(name = "execNum", value = "操作数量")
    private Integer execNum;

    @ApiModelProperty(name = "resourceId", value = "资源ID")
    private Long resourceId;

}
