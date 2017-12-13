package wicket.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件注入
 * 
 * @author Daimon
 * @version N
 * @since 6/6/2014
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentInjector {

    /** 组件的ID */
    String value() default "";

    /** 父类容器组件的ID */
    String parent() default "";

    /** 是否忽略HTML标记 */
    boolean escapeMarkup() default false;

    /** 是否输出ID标记 */
    boolean outputMarkupId() default false;
}