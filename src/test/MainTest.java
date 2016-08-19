package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyp.module.pojo.Customer;

public class MainTest {

	public static void main(String[] args) {
		//ObjectMapper om=new ObjectMapper();
		Customer customer=new Customer();
		customer.setId(1);
		customer.setName("Gavin");
		customer.setSalt("00000000011111111");
		/*try {
			om.writeValue(new File("D://customer.json"), customer);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("D://customer.obj"));
			out.writeObject(customer);
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("D://customer.obj"));
			Customer inCustomer=(Customer) in.readObject();
			System.out.println(inCustomer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
