package com.today.roc.go.dto.request.cost;

import com.today.roc.go.dto.request.SimplePageBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年06月10日 16:22*
 * log.info()
 */
@Data
@ApiModel("商户消耗统计Dto")
public class CostTimeTotalRecordDto extends SimplePageBean implements Serializable {

    @ApiModelProperty("平台 管理端:admin 商户端:merchant")
    @NotBlank(message = "平台类型不可为空")
    private String platform;

    @ApiModelProperty("商户ID组")
    private List<Long> merchantIds;

}
