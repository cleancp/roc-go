package com.today.designPatterns.factoryPattern.factoryMethod;

import com.today.designPatterns.factoryPattern.pojo.Product;
import com.today.designPatterns.factoryPattern.pojo.ProductOne;
import lombok.extern.slf4j.Slf4j;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月30日 12:11*
 * log.info()
 */
@Slf4j
public class ProductFactory extends FactoryMethod {
    @Override
    public Product createObj(String type) {
        if (EnumObjType.ObjOne.getCode().equals(type)) {
            return new ProductOne();
        }
        return null;
    }

    public static void main(String[] args) {
        FactoryMethod factoryMethod = new ProductFactory();
        Product product = factoryMethod.product(EnumObjType.ObjOne.getCode(), "iphone12");
        if (product instanceof ProductOne) {
            ProductOne productOne = (ProductOne) product;
            log.info("ProductOne{}", productOne);
        }

    }

}
