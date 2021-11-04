package com.today.roc.go.understand.java8._02funcinterface;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年11月04日 23:49*
 * log.info()
 */
@Slf4j
@Data
public class FuncHandler<T> {

    private List<T> successList = Lists.newArrayList();

    private List<T> errorList = Lists.newArrayList();

    public void handel(String msg , List<FuncExecutor<T>> executorList){
        log.info(msg);
        List<T> tempList = Lists.newArrayList();
        for (T t : successList) {
            log.info("循环体 success size:{}",successList.size());
            for (FuncExecutor<T> executor : executorList) {
                if (executor.getPredicate().test(t)){
                    log.info("校验失败");
                    executor.getBiConsumer().accept(t,executor.getErrorMsg());
                    errorList.add(t);
                }else {
                    log.info("校验成功");
                    tempList.add(t);
                }
                log.info("成功数量：{} 失败数量:{}",tempList.size(),errorList.size());
            }
        }
        successList.clear();
        successList.addAll(tempList);
    }

}
