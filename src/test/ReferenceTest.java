package test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * ʵ����������Ϊ����˵���˸������õĴ���ʽ��
 * 1.ǿ���ã�new��ʽ
 * 2.soft�����ã���JVM�ڴ�ռ䲻��ʱ������(�����û��գ���������������ǿ���ò��ɴ�)
 * 3.weak�����ã�����������ʱ�������������(�����û��գ���������������ǿ���ò��ɴ�)
 * 4.Phantom�����ã�  �������ڼ������ʱ��û��ͨ�������������Զ������ͨ�������ÿɵ���Ķ�����Ȼ����ԭ״��ֱ�������������ö���������������Ƕ���ò��ɵ�� 
 * @author earthlyfish
 *
 */
public class ReferenceTest {
	/*
	 * ReferenceQueue������:
	 * <p>���ѱ��������յĶ����¼�ڶ����У����ǿ��Ը�����������е����ö���
	 * ֪����Щ�Ѿ������գ��ý�����һ����������:
	 * ����:WeakHashMap�������� ReferenceQueue�����key�Ѿ�û��ǿ���õ� Entries.</p>
	 * <br/>
	 * <p>1.poll():��ѯ�˶��У��鿴�Ƿ���ڿ��õ����ö���
	 * �������һ���������õĶ�����Ӹö������Ƴ��˶��󲢷��ء�����˷����������� null.</p>
	 * <br/>
	 * <p>2.remove():�Ƴ��˶����е���һ�����ö�����������һ�������ÿ��û��߸����ĳ�ʱ��(��Ȼ�����Զ��峬ʱʱ��)����Ϊֹ��</p>
	 * <br/>
	 */
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

	public static void checkQueue() {
		Reference<? extends VeryBig> ref = null;
		while ((ref = rq.poll()) != null) {
			if (ref != null) {
				System.out.println("In queue: " + ((VeryBigWeakReference) (ref)).id);
			}
		}
	}

	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		int size = 1;
		LinkedList<WeakReference<VeryBig>> weakList = new LinkedList<WeakReference<VeryBig>>();
		VeryBig vb=new VeryBig("Weak ");
		for (int i = 0; i < size; i++) {
			weakList.add(new VeryBigWeakReference(vb, rq));
			System.out.println("Just created weak: " + weakList.getLast());
		}
		vb=null;
		System.gc();
		try { // ������Ϣ�����ӣ�����������������߳��������
			Thread.currentThread().sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		checkQueue();
	}

}

class VeryBig {
	public String id;
	// ռ�ÿռ�,���߳̽��л���
	byte[] b = new byte[2 * 1024];

	public VeryBig(String id) {
		this.id = id;
	}

	protected void finalize() {
		System.out.println("Finalizing VeryBig " + id);
	}
}

class VeryBigWeakReference extends WeakReference<VeryBig> {
	public String id;

	public VeryBigWeakReference(VeryBig big, ReferenceQueue<VeryBig> rq) {
		super(big, rq);
		this.id = big.id;
	}

	protected void finalize() {
		System.out.println("Finalizing VeryBigWeakReference " + id);
	}
}
