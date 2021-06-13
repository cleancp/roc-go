package com.today.roc.go.understand.designPatterns.factoryPattern.simpleFactory;

import com.today.roc.go.understand.designPatterns.factoryPattern.pojo.Product;
import com.today.roc.go.understand.designPatterns.factoryPattern.pojo.ProductOne;
import com.today.roc.go.understand.designPatterns.factoryPattern.pojo.ProductTwo;

import java.util.Objects;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月30日 11:07*
 * log.info()
 */
public class SimpleFactory {

    public static Product createObj(EnumObjType enumObjType) {
        if (EnumObjType.ObjOne.equals(enumObjType)) {
            return new ProductOne();
        } else if (EnumObjType.ObjTwo.equals(enumObjType)) {
            return new ProductTwo();
        }
        return null;
    }

    public enum EnumObjType {
        ObjOne("one"),
        ObjTwo("two");
        String code;

        EnumObjType(String code) {
            this.code = code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
