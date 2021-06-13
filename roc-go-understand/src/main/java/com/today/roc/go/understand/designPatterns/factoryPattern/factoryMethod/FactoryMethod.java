package com.today.roc.go.understand.designPatterns.factoryPattern.factoryMethod;

import com.today.roc.go.understand.designPatterns.factoryPattern.pojo.Product;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月30日 12:07*
 * log.info()
 */
public abstract class FactoryMethod {

    public abstract Product createObj(String type);

    public Product product(String code, String name) {
        Product obj = createObj(code);
        obj.setName(name);
        return obj;
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

        public String getCode() {
            return code;
        }
    }

}
