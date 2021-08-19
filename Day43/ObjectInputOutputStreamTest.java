package Day43;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: ObjectInputOutputStreamTest
 * @Description: 对象流的使用
 * @Author: TianXing.Xue
 * @Date: 2021/8/8 15:54
 *
 *  1.ObjectInputStream 和 ObjectOutputStream
 *  ObjectOutputStream：序列化！！ 内存中的对象 ---> 存储中的文件、通过网络传输出去
 *  ObjectInputStream:反序列化！！ 存储中的文件、通过网络接收过来 ---> 还原为内存中的对象
 *
 *  2.作用：用于存储和读取基本数据类型或对象的处理流。它的强大之处就是可以把Java中的对象
 *
 *  3.要想一个java对象时可序列化的，需要满足相应的要求。见Person.java
 *
 *  4.序列化机制
 *   对象序列化机制允许把内存中的java对象转换成平台无关的二进制流，从而允许这种二进制流持久地保存在磁盘上，或通过网络将
 *   这种二进制流传输到另一个网络节点。当其他程序获取了这种二进制流，就可以恢复成原来的JAVA对象
 *
 **/

public class ObjectInputOutputStreamTest {

    /*
    序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
    使用ObjectOutputStream实现

     */

    @Test
    public void testObjectOutputStream() {
        ObjectOutputStream oos = null;
        try {
            //1.
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            //2.
            oos.writeObject(new String("我爱北京"));
            oos.flush(); //刷新操作

            oos.writeObject(new Person("马云", 23));
            oos.flush();

            oos.writeObject(new Person("马化腾",14,1001,new Account(5000)));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    反序列化：将磁盘文件中的对象还原为内存中的一个java对象
    使用ObjectInputStream来实现

     */
    @Test
    public void testObjectInputStream() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object object = ois.readObject();
            String str = (String) object;

            Person p = (Person) ois.readObject();
            Person p1 = (Person) ois.readObject();

            System.out.println(str);
            System.out.println(p);
            System.out.println(p1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
