package com.wjx.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
 
public class SpELTest {
 
    public static void main(String[] args) {
 
    	//创建ExpressionParser解析表达式
        ExpressionParser parser = new SpelExpressionParser();
        //SpEL表达式语法设置在parseExpression()入参内
        Expression exp = parser.parseExpression("表达式");
        //执行SpEL表达式，执行的默认Spring容器是Spring本身的容器：ApplicationContext
        Object value = exp.getValue();
        
 
        /**也可以使用非Spring的ApplicationContext容器，则用下面的方法*/
        //创建一个虚拟的容器EvaluationContext
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        //向容器内添加bean
        BeanA beanA = new BeanA();
        ctx.setVariable("bean_id", beanA);
        //setRootObject并非必须；一个EvaluationContext只能有一个RootObject，引用它的属性时，可以不加前缀
        ctx.setRootObject("XXX");
        //getValue有参数ctx，从新的容器中根据SpEL表达式获取所需的值
        Object value1 = exp.getValue(ctx);
    }
}