package com.today.roc.go.dto.response.cost;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年06月09日 18:29*
 * log.info()
 */
@ApiModel("商户总览")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostTimeRecordVo implements Serializable {

    @ApiModelProperty("商户ID")
    private Long       merchantId;
    @ApiModelProperty("商户名称")
    private String     merchantName;
    @ApiModelProperty("总充值时长")
    private BigDecimal rechargeTime;
    @ApiModelProperty("总剩余时长")
    private BigDecimal remainTime;
    @ApiModelProperty("总消耗时长")
    private BigDecimal costTime;
    @ApiModelProperty("实际总消耗时长")
    private BigDecimal realCostTime;

}
