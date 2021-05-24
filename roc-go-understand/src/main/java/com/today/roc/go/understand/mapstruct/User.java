package com.today.roc.go.understand.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * description：
 * author：roc.zou
 * 2021/5/18 6:45 下午
 */
@Data
@Accessors(chain = true)
public class User {

    private Long id;

    private Integer sex;

    private String name;

    private BigDecimal assets;


}
