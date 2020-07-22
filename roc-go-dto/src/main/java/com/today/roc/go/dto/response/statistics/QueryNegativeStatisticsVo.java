package com.today.roc.go.dto.response.statistics;

import io.swagger.annotations.ApiModel;
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
 * @createTime 2020年04月09日 14:04*
 * log.info()
 */
@ApiModel("反向录音质检统计Vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryNegativeStatisticsVo extends QueryCommonStatisticsVo implements Serializable {

    private List<RuleStatisticsVo> ruleStatisticsVos;

}
