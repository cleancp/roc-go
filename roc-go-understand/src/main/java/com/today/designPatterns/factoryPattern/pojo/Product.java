package com.today.designPatterns.factoryPattern.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月30日 11:12*
 * log.info()
 */
@Data
public class Product {

    public String     name;
    public String     pictureUrl;
    public BigDecimal price;
}
