package com.today.roc.go.dto.response.homepage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
 * @Description 其它图表
 * @createTime 2020年02月25日 09:15*
 * log.info()
 */
@ApiModel(value = "其它图表数据")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeOthersVo implements Serializable {


    @ApiModelProperty(value = "关键词组ID")
    private Long keyWordTeamId;

    //关键词命中排名
    private List<HomeKeyWordVo> keyWordVos;

    //分公司命中
    private List<HomeSeatCompanyVo>    seatVos;

    //机构命中
    private List<HomeCustVo>    custVos;

}
