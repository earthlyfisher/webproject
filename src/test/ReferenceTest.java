package test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * 实例以弱引用为例，说明了各种引用的处理方式：
 * 1.强引用：new方式
 * 2.soft软引用：在JVM内存空间不足时，回收(软引用回收，必须条件，对象强引用不可达)
 * 3.weak弱引用：在垃圾回收时，触发对象回收(弱引用回收，必须条件，对象强引用不可达)
 * 4.Phantom虚引用：  虚引用在加入队列时并没有通过垃圾回收器自动清除。通过虚引用可到达的对象将仍然保持原状，直到所有这类引用都被清除，或者它们都变得不可到达。 
 * @author earthlyfish
 *
 */
public class ReferenceTest {
	/*
	 * ReferenceQueue的作用:
	 * <p>将已被垃圾回收的对象记录在队列中，我们可以根据这个队列中的引用对象，
	 * 知道哪些已经被回收，好进行下一步的清理工作:
	 * 比如:WeakHashMap就是利用 ReferenceQueue来清除key已经没有强引用的 Entries.</p>
	 * <br/>
	 * <p>1.poll():轮询此队列，查看是否存在可用的引用对象。
	 * 如果存在一个立即可用的对象，则从该队列中移除此对象并返回。否则此方法立即返回 null.</p>
	 * <br/>
	 * <p>2.remove():移除此队列中的下一个引用对象，阻塞到有一个对象变得可用或者给定的超时期(当然可以自定义超时时间)满了为止。</p>
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
		try { // 下面休息几分钟，让上面的垃圾回收线程运行完成
			Thread.currentThread().sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		checkQueue();
	}

}

class VeryBig {
	public String id;
	// 占用空间,让线程进行回收
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
