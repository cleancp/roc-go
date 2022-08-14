package com.today.泛型;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年12月15日 23:45*
 * log.info()
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstanceT<T> {

    private T key;

    public <T> InstanceT<T> makeInstance(Class<T> cl) {
        try {
            return new InstanceT<>(cl.newInstance());
        } catch (Exception e) {
            return null;
        }
    }

}
