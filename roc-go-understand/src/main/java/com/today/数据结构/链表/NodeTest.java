package com.today.数据结构.链表;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月21日 16:46*
 * log.info()
 * <p>
 * 定义链表
 */
public class NodeTest {








    //定义结构
    class Node<V> {
        V       v;
        Node<V> next;

        public Node(V v, Node<V> next) {
            this.v = v;
            this.next = next;
        }

        public Node(V v) {
            this.v = v;
            this.next = null;
        }
    }

}
