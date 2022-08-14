package com.today.response.homepage;

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
 * @Description 首页质检录音量，录音时长图表
 * @createTime 2020年02月25日 09:08*
 * log.info()
 */
@ApiModel(value = "首页质检录音量，录音时长图表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomePageVo implements Serializable {

    //今日质检录音量（件）
    @ApiModelProperty(value = "今日质检录音量（件）")
    private Long todayInspectionNum;
    //今日质检录音时长（分）
    @ApiModelProperty(value = "今日质检录音时长（分）")
    private Long todayInspectionTime;
    //总质检录音量（件）
    @ApiModelProperty(value = "总质检录音量（件）")
    private Long totalInspectionNum;
    //总质检录音时长（分）
    @ApiModelProperty(value = "总质检录音时长（分）")
    private Long totalInspectionTime;

}
