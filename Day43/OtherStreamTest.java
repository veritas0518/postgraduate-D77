package Day43;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: OtherStreamTest
 * @Description: 其他流的使用
 * @Author: TianXing.Xue
 * @Date: 2021/8/8 9:51
 *
 *  1.标准的输入、输出流
 *  2.打印流
 *  3.数据流
 *
 *   1.标准的输入、输出流
 *     System.in:标准的输入流，默认从键盘输入
 *     System.out:标准的输出流，默认从控制台输出
 *
 *     1.2
 *     System类的setIn(InputStream in) / setOut(InputStream out)方式重新指定输入和输出的流
 *
 *     1.3练习：
 *     从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续惊醒输入操作，直至当输入“e”或者“exit”时，退出程序
 *
 *     方法一：使用Scanner实现，调用next()返回一个字符串
 *     方法二：使用System.in实现。System.in  ---> 转换流 ---> BufferedReader 的readline()
 *
 *   2.打印流：PrintStream 和 PrintWriter
 *
 *      2.1 提供了一系列重载的print() 和 println()
 *
 *   3. 数据流
 *
 *      3.1 DataInputStream 和 DataOutputStream
 *
 *      3.2 作用：用于读取或写出基本数据类型的变量或字符串
 *
 *
 **/

public class OtherStreamTest {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);

            while (true) {
                System.out.println("请输入字符串：");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    break;
                } else {
                    String upperCase = data.toUpperCase();
                    System.out.println(upperCase);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    练习：将内存中的字符串、基本数据类型的变量写出到文件中。


     */
    @Test
    public void testDataOutputStream() {

        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("data.txt"));
            dos.writeUTF("马云");
            dos.flush(); //执行flush()，刷新操作，将内存中的数据写入文件
            dos.writeInt(23);
            dos.flush();
            dos.writeBoolean(true);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dos!=null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中。
    注意点：读取不同类型的数据的顺序要与当初写入文件时，保存的数据的顺序一致！

     */
    @Test
    public void testDataInputStream(){

        //1.
        String name = null;
        int age = 0;
        boolean isMale = false;
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));

            //2.
            name = dis.readUTF();
            age = dis.readInt();
            isMale = dis.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("name = "+name);
        System.out.println("age = "+age);
        System.out.println("是不是男的"+isMale);
    }
}
