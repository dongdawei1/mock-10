package com.mock.utils.forcheck;

/*
 * 使用java8 Lambda 表达式必须要有一个接口且接口中只有一个抽象方法
 */
public interface RunnableSupportingThrowingException {
    void run() throws Exception;
}
