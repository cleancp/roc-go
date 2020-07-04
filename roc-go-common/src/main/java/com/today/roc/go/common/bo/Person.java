package com.today.roc.go.common.bo;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月02日 17:45*
 * log.info()
 */
public class Person {
    private int    id;
    private String name;
    private String address;

    public Person() {
    }

    public Person(int id, String name, String address) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override

    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", address=" + address + "]";
    }
}