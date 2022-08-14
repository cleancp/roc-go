package com.today.request.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.today.request.SimplePageBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年04月19日 18:31*
 * log.info()
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonStatisticsDto extends SimplePageBean implements Serializable {

    @ApiModelProperty(value = "商户id" , hidden = true)
    private Long merchantId;

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

    @ApiModelProperty("关系")
    private String customerRelation;

    @ApiModelProperty("分组字段")
    private List<String> groupFields;

    @ApiModelProperty("录音时间start")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordDateStart;

    @ApiModelProperty("录音时间end")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordDateEnd;
}
