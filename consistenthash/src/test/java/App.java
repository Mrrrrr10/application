import org.junit.Test;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public class App {

	@Test
	public void testHashCode() {
		System.out.println("192.168.0.0:111的哈希值：" + "192.168.0.0:1111".hashCode());
		System.out.println("192.168.0.1:111的哈希值：" + "192.168.0.1:1111".hashCode());
		System.out.println("192.168.0.2:111的哈希值：" + "192.168.0.2:1111".hashCode());
		System.out.println("192.168.0.3:111的哈希值：" + "192.168.0.3:1111".hashCode());
		System.out.println("192.168.0.4:111的哈希值：" + "192.168.0.4:1111".hashCode());

		System.out.println("192.168.1.0:1111的哈希值：" + "192.168.1.0:1111".hashCode());
	}

}
