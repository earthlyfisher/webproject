package test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream 具有读入功能的抽象被装饰器。
 * 
 * FileInputStream 具有读入文件功能的具体被装饰器
 * 
 * FilterInputStream具备装饰器的抽象意义
 * 
 * BufferedInputStream 具有具体功能 ( 缓冲功能 ) 的装饰器
 * 
 * @author earthlyfish
 *
 */
public class IoDecoratorTest {

	public static void main(String[] args) throws Exception {
		// FilterInputStream
		InputStream in = new FileInputStream("");
		// FilterInputStream:构造函数是protected,不能new;FilterInputStream是根装饰类
		// BufferedInputStream继承自根装饰类FilterInputStream
		InputStream decIn = new BufferedInputStream(in);
		InputStream decIn2 = new DataInputStream(in);
	}
}
