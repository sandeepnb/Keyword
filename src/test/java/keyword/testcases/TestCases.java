package keyword.testcases;

import org.testng.annotations.Test;

import keyword.executionEngine.KeywordExecution;

public class TestCases {

	
	
	public KeywordExecution keyword;
	
	
	@Test
	public void testCaseLogin()
	{
		keyword=new KeywordExecution();
		keyword.start_Execution("signup");
	}
	
	
	/*
	 * @Test public void testCasesignup() { keyword=new KeywordExecution();
	 * keyword.start_Execution("signup"); }
	 */
	
	
}
