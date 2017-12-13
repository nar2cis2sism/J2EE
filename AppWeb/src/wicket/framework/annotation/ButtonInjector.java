package wicket.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件注入{@link org.apache.wicket.markup.html.form.Button}
 * 
 * @author Daimon
 * @version N
 * @since 6/6/2014
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ButtonInjector {

    /** 提交表单处理方法名称 */
    String onSubmit();
}