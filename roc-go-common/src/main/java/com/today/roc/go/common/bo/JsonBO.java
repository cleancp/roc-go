package com.today.roc.go.common.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月02日 14:24*
 * log.info()
 */
@Data
public class JsonBO implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private Double money;

    private List<JsonSubBO> dataList;

    @Data
    public static class JsonSubBO {
        private Long    id;
        private String  subname;
        private Integer subage;
        private Double  submoney;
    }
}
