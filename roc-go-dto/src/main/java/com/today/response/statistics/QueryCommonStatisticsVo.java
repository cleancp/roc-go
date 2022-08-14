package com.today.response.statistics;

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
 * @createTime 2020年04月09日 14:04*
 * log.info()
 */
@ApiModel("公共质检统计Vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryCommonStatisticsVo implements Serializable {

    @ApiModelProperty("机构")
    private String commitOrgan;

    @ApiModelProperty("分公司")
    private String agentCompany;

    @ApiModelProperty("业务组")
    private String agentGroup;

    @ApiModelProperty("员工工号")
    private String agentAccount;

    @ApiModelProperty("批次号")
    private String batchCode;

    @ApiModelProperty("案件编号")
    private String billCode;

    @ApiModelProperty("录音关系")
    private String customerRelation;

    @ApiModelProperty("总质检量")
    private Long qiCount;

    @ApiModelProperty("录音区间")
    private String recordDateScope;

}
