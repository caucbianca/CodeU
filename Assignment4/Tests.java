package Assignment4;

import org.junit.Test;

import junit.framework.*;

public class Tests {

	@Test
	public void test1() {
		boolean[][] grid = { { false, true, false, true }, 
							 { true, true, false, false },
							 { false, false, true, false }, 
							 { false, false, true, false } };

		Assert.assertEquals(3, CountingIslands.getNumberOfIslands(grid));
	}

	@Test
	public void test2() {
		boolean[][] grid = { { true, false, false, false, true, true, false, false, false, false },
							 { true, true, false, true, false, true, false, true, true, true },
							 { false, true, true, false, false, true, true, false, false, true },
							 { true, false, false, false, true, false, false, true, false, true },
							 { true, true, true, false, true, true, true, true, false, true },
							 { false, true, true, false, false, true, true, false, false, true },
							 { false, false, false, true, true, true, false, true, true, true },
							 { true, true, true, false, false, false, true, false, false, false } };
		Assert.assertEquals(8, CountingIslands.getNumberOfIslands(grid));
	}
	
	@Test
	public void test3() {
		boolean[][] grid = { { true, true },
							 { true, true } };

		Assert.assertEquals(1, CountingIslands.getNumberOfIslands(grid));
	}
}
