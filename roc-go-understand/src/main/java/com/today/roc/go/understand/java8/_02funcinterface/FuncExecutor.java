package com.today.roc.go.understand.java8._02funcinterface;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年11月04日 23:53*
 * log.info()
 */
@Data
@AllArgsConstructor
public class FuncExecutor<T> {

    private Predicate<T>         predicate;
    private BiConsumer<T,String> biConsumer;
    private Consumer<T>          consumer;
    private String               errorMsg;
}
