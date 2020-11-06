package com.mock.constant.teseng;

import org.testng.Assert;

import org.testng.annotations.BeforeSuite;
import static com.mock.utils.forcheck.mysqlutils.MysqlUtils.closeJdbcConnections;
import static com.mock.utils.forcheck.mysqlutils.MysqlUtils.closeJdbcStatements;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BaseTestNg extends Assert{
	
    @BeforeSuite
    public void beforeSuite() {
        log.info("======================================== beforeSuite ========================================");
    }
    
   // @AfterSuite
    public void afterSuite() throws Exception {
      //在这里关闭连接吧
    	closeJdbcConnections();
    	closeJdbcStatements();
    	
    	log.info("======================================== afterSuite ========================================");

    }

    
    
    
    
    
    

}
