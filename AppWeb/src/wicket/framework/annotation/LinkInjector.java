package wicket.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件注入{@link org.apache.wicket.markup.html.link.Link}
 * 
 * @author Daimon
 * @version N
 * @since 6/6/2014
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LinkInjector {

    /** 点击事件处理方法名称 */
    String onClick();
}