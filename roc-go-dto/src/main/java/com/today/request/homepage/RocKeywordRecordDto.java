package com.today.request.homepage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年05月27日 13:55*
 * log.info()
 */
@ApiModel(value = "关键词关联质检记录DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RocKeywordRecordDto {

    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "关键词组ID")
    private Long keyWordTeamId;

    @ApiModelProperty(value = "质检文件类型  media 录音 text 文本")
    @NotBlank
    private String fileType;

    @ApiModelProperty(value = "下拉类型(时间选择 1 今日  2 昨日  3 近3日 4 近一周 5 近一个月)")
    @NotBlank
    private String timeType;

}
