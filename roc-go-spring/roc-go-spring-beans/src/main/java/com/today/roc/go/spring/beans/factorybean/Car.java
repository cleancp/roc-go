package com.today.roc.go.spring.beans.factorybean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月27日 17:54*
 * log.info()
 */
@Data
public class Car {

    private String name;

    private String type;

    private BigDecimal price;
}
