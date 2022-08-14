package com.today.roc.go.web.index;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.today.utils.json.JacksonUtils;
import com.today.request.homepage.RocHomePageDto;
import com.today.request.homepage.RocKeywordRecordDto;
import com.today.response.homepage.HomeOthersVo;
import com.today.response.homepage.HomePageVo;
import com.today.response.homepage.KeyWordTeamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2019年05月09日 19:01*
 * log.info()
 */
@Api(value = "首页", tags = "首页")
@Slf4j
@RestController
@RequestMapping("/homepage")
public class HomePageController {

    /**
     * @description 质检量及时长统计
     **/
    @PostMapping("inspectionStatistics")
    @ApiOperation(value = "质检量及时长统计")
    public HomePageVo inspectionStatistics() {
        return HomePageVo.builder().todayInspectionNum(123341241L).todayInspectionTime(123231L).totalInspectionNum(123131232L).totalInspectionTime(213132L).build();
    }

    /**
     * @description 关键词组命中排名
     **/
    @PostMapping("keyWordComposeStatistics")
    @ApiOperation(value = "关键词组命中排名")
    public List<KeyWordTeamVo> keyWordComposeStatistics(@RequestBody @Valid RocHomePageDto dto) {
        System.out.println(JacksonUtils.toJson(dto));
        return Lists.newArrayList();
    }


    /**
     * @description 关键词, 分公司, 机构命中
     **/
    @PostMapping("othersStatistics")
    @ApiOperation(value = "关键词,分公司,机构命中")
    public HomeOthersVo othersStatistics(@RequestBody @Valid RocHomePageDto dto) {
        System.out.println(JacksonUtils.toJson(dto));
        return new HomeOthersVo();
    }

    @PostMapping("keywordRecordList")
    @ApiOperation(value = "关键词关联的质检记录")
    public void keywordRecordList(@RequestBody @Valid RocKeywordRecordDto dto) throws JsonProcessingException {
        System.out.println(JacksonUtils.toJson(dto));
    }

}
