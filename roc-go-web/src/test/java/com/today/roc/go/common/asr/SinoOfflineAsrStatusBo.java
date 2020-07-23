package com.today.roc.go.common.asr;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: yuboliang
 * @date: 2020/3/5
 **/
@Getter
@Setter
public class SinoOfflineAsrStatusBo {
    /**
     * 任务编号
     */
    private String task_no;
    /**
     * 具体的某些文件(非必填)
     */
    private List<String> files;

}
