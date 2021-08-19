package Day43;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @ClassName: RandomAccessFileTest
 * @Description: RandomAccessFile的使用
 * @Author: TianXing.Xue
 * @Date: 2021/8/8 17:38
 *
 *  1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 *  2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *
 *  3.如果RandomAccessFile作为输出流时。如果写出到的文件不存在，则在执行过程中自动创建
 *    如果写出到的文件存在，则会对原有文件内容覆盖。(默认情况下从头覆盖)
 *
 *  4.可以通过相关的操作，实现RandomAccessFile"插入"数据的效果
 *
 **/

public class RandomAccessFileTest {

    @Test
    public void test1()  {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("友情与爱情.jpg"),"r");
            raf2 = new RandomAccessFile(new File("友情与爱情1.jpg"),"rw");

            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf1.read(buffer))!=-1){
                raf2.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf2!=null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf1!=null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2(){

        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt","rw");

            raf1.write("xtx".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf1!=null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    使用RandomAccessFile实现数据的插入效果
    思考：将StringBuilder 替换为 ByteArrayOutputStream

     */

    @Test
    public void test3(){

        RandomAccessFile raf1= null;
        try {
            raf1 = new RandomAccessFile("hello.txt","rw");

            raf1.seek(3); //将指针调到角标为3的位置
            //保存指针三后面的所有数据到StringBuilder中
            StringBuilder builder  =new StringBuilder((int) new File("hello.txt").length());
            byte[] buffer = new byte[20];
            int len;
            while ((len = raf1.read(buffer))!=-1){
                builder.append(new String(buffer,0,len));
            }
            //调回指针，写入“gkd”
            raf1.seek(3);
            raf1.write("gkd".getBytes());

            //将StringBuilder中的数据写入到文件中
            raf1.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf1!=null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
