package com.today.roc.go.understand.java8._02funcinterface;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年11月04日 23:46*
 * log.info()
 */
@Data
public class FuncBean {

    private String name;
    private String errorMsg;
}
