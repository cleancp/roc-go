package com.today.泛型;

import org.checkerframework.checker.units.qual.K;

import java.io.Serializable;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年12月14日 07:13*
 * log.info()
 */
public class Single<T> {
    private T t;

    /**
     * @param <K> key
     * @param <V> value
     */
    public static class Duality<K,V extends Comparable & Serializable>{
        private K k;
        private V v;
    }

    /**
     * 原始类型
     * 如果V extends  Serializable & Comparable
     * private Serialize v
     */
    public static class OriginDuality{
        private Object k;
        private Comparable v;
    }


}


