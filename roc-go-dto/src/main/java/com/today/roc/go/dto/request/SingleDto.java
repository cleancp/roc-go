package com.today.roc.go.dto.request;

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
 * @createTime 2020年04月20日 11:51*
 * log.info()
 */
@ApiModel("分组字段Dto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleDto<T> implements Serializable {

    @ApiModelProperty(value = "qi质检统计 negative 反向 positive 正向")
    private T key;

}
