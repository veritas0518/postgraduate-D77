package Day43;

import java.io.Serializable;

/**
 * @ClassName: Person
 * @Description: Person类需要满足如下的要求方可序列化
 * @Author: TianXing.Xue
 * @Date: 2021/8/8 16:08
 *
 *  1.需要实现接口：Serializable
 *  2.当前类提供一个全局常量：serialVersionUID
 *  3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的。String本身就是序列化的
 *    (默认情况下，基本数据类型也是可序列化的)
 *
 *  补充：ObjectOutputStream 和 ObjectInputStream不能序列化static 和 transient 修饰的成员变量
 *
 **/

public class Person implements Serializable {

    public static final long serialVersionUID = 432322L;

    private String name;
    private int age;
    private int id;
    private Account acct;

    public Person(String name, int age, int id, Account acct) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.acct = acct;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", acct=" + acct +
                '}';
    }
}

class Account implements Serializable {
    public static final long serialVersion = 21313131L;

    private double balance;

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}