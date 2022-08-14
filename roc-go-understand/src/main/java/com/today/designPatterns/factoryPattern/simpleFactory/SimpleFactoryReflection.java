package com.today.designPatterns.factoryPattern.simpleFactory;

import com.google.common.collect.Maps;
import com.today.designPatterns.factoryPattern.pojo.ProductTwo;
import com.today.designPatterns.factoryPattern.pojo.Product;
import com.today.designPatterns.factoryPattern.pojo.ProductOne;

import java.util.Map;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月30日 11:39*
 * log.info()
 */
public class SimpleFactoryReflection {

    private static final Map<EnumObjType, Class> map = Maps.newHashMap();

    private static final Map<EnumObjType, Product> productMap = Maps.newHashMap();

    public static void addProductKey(EnumObjType enumObjType, Class productClass) {
        map.put(enumObjType, productClass);
    }

    public static void addProductKey(EnumObjType enumObjType) {
        if (EnumObjType.ObjOne.equals(enumObjType)) {
            productMap.put(enumObjType, new ProductOne());
        } else if (EnumObjType.ObjTwo.equals(enumObjType)) {
            productMap.put(enumObjType, new ProductTwo());
        }
    }


    public static Product createObj(EnumObjType enumObjType) throws IllegalAccessException, InstantiationException {
        Class aClass = map.get(enumObjType);
        return (Product) aClass.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        addProductKey(EnumObjType.ObjOne, ProductOne.class);
        ProductOne obj = (ProductOne) createObj(EnumObjType.ObjOne);
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
