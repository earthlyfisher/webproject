package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.wyp.module.pojo.Customer;

public class MainTest {

	@Test
	public void breakUnmodifiableCollection() {
		
	}

	public static void main(String[] args) {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			Customer customer = new Customer();
			customer.setId(1);
			customer.setName("Gavin");
			customer.setSalt("00000000011111111");
			File file = new File("D://customer.obj");
			// Serialization
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(customer);

			// deserialization
			in = new ObjectInputStream(new FileInputStream(file));
			Customer inCustomer = (Customer) in.readObject();
			System.out.println(inCustomer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("happened to deserialization");
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
