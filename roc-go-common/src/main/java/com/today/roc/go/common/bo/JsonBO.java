package com.today.roc.go.common.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Expose(deserialize = false)
    @SerializedName(value = "xid",alternate = "id")
    private Long id;

    @Expose(serialize = false)
    @SerializedName(value = "xname",alternate = {"yname","zname","name"})
    private String name;

    @Expose
    @SerializedName(value = "AGE",alternate = {"xage","age"})
    private Integer age;

    @Expose
    @SerializedName(value = "MONEY",alternate = "money")
    private transient Double money;

    @Expose
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date birthday;

    @Expose
    @SerializedName(value = "xlist",alternate = "dataList")
    private List<JsonSubBO> dataList;

    @SerializedName(value = "xmap",alternate = "dataMap")
    private Map<String,JsonSubBO> dataMap;

    @Data
    public static class JsonSubBO implements Serializable{
        @SerializedName(value = "xxid",alternate = "id")
        private Long    id;
        private String  subname;
        private Integer subage;
        private Double  submoney;
        private Date    subbirthday;
    }
}
