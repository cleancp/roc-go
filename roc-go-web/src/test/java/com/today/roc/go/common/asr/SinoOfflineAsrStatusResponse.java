package com.today.roc.go.common.asr;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: yuboliang
 * @date: 2020/3/4
 **/
@Getter
@Setter
public class SinoOfflineAsrStatusResponse {
    /**
     * 任务编号
     */
    private String task_no;
    /**
     * 返回码
     */
    private String res_code;
    /**
     * 返回码的描述
     */
    private String res_msg;
    /**
     * 任务的总文件量
     */
    private Integer total_count;
    /**
     * 转写完成的数量，包括成功与失败
     */
    private Integer finished_count;
    /**
     * 转写成功的数量
     */
    private Integer succeeded_count;
    /**
     * 转写失败的数量
     */
    private Integer failed_count;
    /**
     * 文件详情
     */
    private List<FileStatus> status_list;


    @Getter
    @Setter
    public static class FileStatus {
        /**
         * 文件名
         */
        private String file;
        /**
         * 返回码
         */
        private Integer code;
        /**
         * 返回码描述
         */
        private String msg;
    }
}
