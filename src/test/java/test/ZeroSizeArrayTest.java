package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class ZeroSizeArrayTest {

	@Test
	public void test(){
		List<String> strs = new ArrayList<>();
		for (String string : strs) {
			System.out.println(string);
		}
	}
}
