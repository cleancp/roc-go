package com.today.designPatterns.factoryPattern.simpleFactory;

import com.today.designPatterns.factoryPattern.pojo.ProductOne;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月30日 11:16*
 * log.info()
 */
public class Main {

    public static void main(String[] args) {
        ProductOne product = (ProductOne) SimpleFactory.createObj(SimpleFactory.EnumObjType.ObjOne);


    }

}
