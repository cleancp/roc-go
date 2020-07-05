package com.today.roc.go.common.utils.lamda;

import com.today.roc.go.common.bo.JsonBO;
import com.today.roc.go.common.utils.json.JsonUtils;

import java.util.List;
import java.util.Map;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月05日 22:44*
 * log.info()
 */
public class LamdaCorn {

    public static void main(String[] args) {
        JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 10);
        List<JsonBO.JsonSubBO> dataList = jsonBO.getDataList();
        Map<String, JsonBO.JsonSubBO> dataMap = jsonBO.getDataMap();
        

    }

}
