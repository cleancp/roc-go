package com.today.roc.go.dto.request.homepage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description 首页报表请求DTO
 * @createTime 2020年02月24日 18:21*
 * log.info()
 */
@ApiModel(value = "首页报表请求DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomePageDto implements Serializable {

    @ApiModelProperty(value = "关键词组ID(关键词组命中排名接口不需要传)")
    private Long keyWordTeamId;

    @ApiModelProperty(value = "质检文件类型  media 录音 text 文本")
    @NotBlank
    private String fileType;

    @ApiModelProperty(value = "下拉类型(时间选择 1 今日  2 昨日  3 近3日 4 近一周 5 近一个月)")
    @NotBlank
    private String timeType;
}
