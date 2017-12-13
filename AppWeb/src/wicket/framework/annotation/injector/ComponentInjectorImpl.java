package wicket.framework.annotation.injector;

import engine.java.util.extra.ReflectObject;
import engine.java.util.string.TextUtils;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;

import wicket.framework.annotation.ButtonInjector;
import wicket.framework.annotation.ComponentInjector;
import wicket.framework.annotation.FormInjector;
import wicket.framework.annotation.LinkInjector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
class ComponentInjectorImpl {

    private interface ComponentConstructor<A extends Annotation> {
        
        Component newComponent(Object target, Field field, String id, A annotation);
    }

    private static class LinkConstructor implements ComponentConstructor<LinkInjector> {
    
        @Override
        public Component newComponent(Object target, Field field, String id, LinkInjector annotation) {
            final MethodInvoker invoker = new MethodInvoker(target, field, annotation.onClick());
            Class<?> type = field.getType();
            if (type == Link.class)
            {
                return new Link(id) {
                    @Override
                    public void onClick() {
                        invoker.invoke(this);
                    }
                };
            }
            
            if (type == StatelessLink.class)
            {
                return new StatelessLink(id) {
                    @Override
                    public void onClick() {
                        invoker.invoke(this);
                    }
                };
            }
            
            return null;
        }
    }
    
    private static class FormConstructor implements ComponentConstructor<FormInjector> {

        @Override
        public Component newComponent(Object target, Field field, String id, FormInjector annotation) {
            final MethodInvoker invoker = new MethodInvoker(target, field, annotation.onSubmit());
            Class<?> type = field.getType();
            if (type == Form.class)
            {
                return new Form(id) {
                    @Override
                    protected void onSubmit() {
                        invoker.invoke(this);
                    }
                };
            }
            
            if (type == StatelessForm.class)
            {
                return new StatelessForm(id) {
                    @Override
                    protected void onSubmit() {
                        invoker.invoke(this);
                    }
                };
            }
            
            return null;
        }
    }
    
    private static class ButtonConstructor implements ComponentConstructor<ButtonInjector> {

        @Override
        public Component newComponent(Object target, Field field, String id, ButtonInjector annotation) {
            final MethodInvoker invoker = new MethodInvoker(target, field, annotation.onSubmit());
            return new Button(id) {
                @Override
                public void onSubmit() {
                    invoker.invoke(this);
                }
            };
        }
    }
    
    private static class AjaxLinkConstructor implements ComponentConstructor<Annotation> {

        @Override
        public Component newComponent(Object target, Field field, String id, Annotation annotation) {
            final AjaxInvoker invoker = new AjaxInvoker(target);
            Class<?> type = field.getType();
            if (type == AjaxLink.class)
            {
                return new AjaxLink(id) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        invoker.invoke(this, target);
                    }
                };
            }
            
            if (type == AjaxFallbackLink.class)
            {
                return new AjaxFallbackLink(id) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        invoker.invoke(this, target);
                    }
                };
            }
            
            if (type == IndicatingAjaxLink.class)
            {
                return new IndicatingAjaxLink(id) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        invoker.invoke(this, target);
                    }
                };
            }
            
            if (type == AjaxSubmitLink.class)
            {
                return new AjaxSubmitLink(id) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        invoker.invoke(this, target);
                    }
                };
            }
            
            return null;
        }
    }
    
    private static class AjaxButtonConstructor implements ComponentConstructor<Annotation> {

        @Override
        public Component newComponent(Object target, Field field, String id, Annotation annotation) {
            final AjaxInvoker invoker = new AjaxInvoker(target);
            Class<?> type = field.getType();
            if (type == AjaxButton.class)
            {
                return new AjaxButton(id) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        invoker.invoke(this, target);
                    }
                };
            }
            
            if (type == AjaxFallbackButton.class)
            {
                return new AjaxFallbackButton(id, null) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        invoker.invoke(this, target);
                    }
                };
            }
            
            if (type == IndicatingAjaxButton.class)
            {
                return new IndicatingAjaxButton(id) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        invoker.invoke(this, target);
                    }
                };
            }
            
            return null;
        }
    }
    
    private static final HashMap<Class, ComponentConstructor> CONSTRUCTOR_MAP
    = new HashMap<>();
    
    static
    {
        CONSTRUCTOR_MAP.put(LinkInjector.class, new LinkConstructor());
        CONSTRUCTOR_MAP.put(FormInjector.class, new FormConstructor());
        CONSTRUCTOR_MAP.put(ButtonInjector.class, new ButtonConstructor());

        CONSTRUCTOR_MAP.put(AjaxLink.class, new AjaxLinkConstructor());
        CONSTRUCTOR_MAP.put(AjaxFallbackLink.class, new AjaxLinkConstructor());
        CONSTRUCTOR_MAP.put(IndicatingAjaxLink.class, new AjaxLinkConstructor());
        CONSTRUCTOR_MAP.put(AjaxSubmitLink.class, new AjaxLinkConstructor());
        CONSTRUCTOR_MAP.put(AjaxButton.class, new AjaxButtonConstructor());
        CONSTRUCTOR_MAP.put(AjaxFallbackButton.class, new AjaxButtonConstructor());
        CONSTRUCTOR_MAP.put(IndicatingAjaxButton.class, new AjaxButtonConstructor());
    }
    
    private final Field field;
    private final String componentId;
    
    private final Annotation annotation;
    private final ComponentConstructor<Annotation> constructor;
    
    public ComponentInjectorImpl(Field field, String componentId) {
        this.field = field;
        this.componentId = TextUtils.isEmpty(componentId) ? field.getName() : componentId;

        annotation = filterAnnotation(field);
        if (annotation == null)
        {
            constructor = CONSTRUCTOR_MAP.get(field.getType());
        }
        else
        {
            constructor = CONSTRUCTOR_MAP.get(annotation.annotationType());
        }
    }
    
    private static Annotation filterAnnotation(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations)
        {
            if (annotation instanceof ComponentInjector)
            {
                continue;
            }
            else
            {
                return annotation;
            }
        }
        
        return null;
    }
    
    public final Field getField() {
        return field;
    }
    
    public final String getComponentId() {
        return componentId;
    }
    
    public Component inject(Object target) throws Exception {
        Component component;
        if (constructor == null)
        {
            Class<?> type = field.getType();
            if (type.isMemberClass())
            {
                Constructor<?> constructor = type.getDeclaredConstructor(target.getClass(), String.class);
                constructor.setAccessible(true);
                component = (Component) constructor.newInstance(target, componentId);
            }
            else
            {
                Constructor<?> constructor = type.getDeclaredConstructor(String.class);
                constructor.setAccessible(true);
                component = (Component) constructor.newInstance(componentId);
            }
        }
        else
        {
            component = constructor.newComponent(target, field, componentId, annotation);
        }
        
        field.set(target, component);
        return component;
    }
}

class MethodInvoker {
    
    private ReflectObject ref;
    private Method method;
    private boolean withArgs;
    
    public MethodInvoker(Object target, Field field, String method) {
        if (TextUtils.isEmpty(method))
        {
            return;
        }
        
        ref = new ReflectObject(target);
        Method m = ref.getMethod(method);
        if (m == null)
        {
            withArgs = true;
            m = ref.getMethod(method, field.getType());
        }
        
        this.method = m;
    }
    
    public void invoke(Component component) {
        if (ref == null) return;
        if (method == null) return;
        try {
            if (withArgs)
            {
                ref.invoke(method, component);
            }
            else
            {
                ref.invoke(method);
            }
        } catch (Exception e) {
            throw new RuntimeException("Invoke error--" + method, e);
        }
    }
}

class AjaxInvoker {
    
    private final ReflectObject ref;
    private final Method method;
    
    public AjaxInvoker(Object target) {
        ref = new ReflectObject(target);
        method = ref.getMethod("onAjax", Component.class, AjaxRequestTarget.class);
    }
    
    public void invoke(Component component, AjaxRequestTarget target) {
        if (method == null) return;
        try {
            ref.invoke(method, component, target);
        } catch (Exception e) {
            throw new RuntimeException("Ajax error", e);
        }
    }
}