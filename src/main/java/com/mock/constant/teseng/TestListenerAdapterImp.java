package com.mock.constant.teseng;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListenerAdapterImp extends TestListenerAdapter {
    /**
     * 获取每个执行方法的结果，冒烟使用如果有失败和跳过停止运行
     * */
	
	private int m_count = 0;
    
    @Override
    public void onTestFailure(ITestResult tr) {
        log(tr.getName()+ "--Test method failed\n");
    }
     
    @Override
    public void onTestSkipped(ITestResult tr) {
        log(tr.getName()+ "--Test method skipped\n");
    }
     
    @Override
    public void onTestSuccess(ITestResult tr) {
        log(tr.getName()+ "--Test method success\n");
    }
     
    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
        System.out.println("");
        }
    }

}