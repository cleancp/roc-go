package com.today.roc.go.understand.lombok;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * description：
 * author：roc.zou
 * 2021/5/18 7:00 下午
 */
@Accessors(chain = true, prefix = "p")
@Data
public class User {

    private String name;
    private String pName;

    public static void main(String[] args) {
        //@Accessors(chain = true)  get/set返回当前对象
        /**
         * public String getName() {
         return this.name;
         }

         public User setName(String name) {
         this.name = name;
         return this;
         }*/
        //@Accessors(fluent = true)
        /**
         * public String name() {
         return this.name;
         }

         public User name(String name) {
         this.name = name;
         return this;
         }
         */

        //@Accessors(chain = true,prefix = "p")  忽略前缀使用驼峰
        /**
         * public String getName() {
        return this.pName;
    }

    public User setName(String pName) {
        this.pName = pName;
        return this;
    }*/
        User user = new User();
    }
}
