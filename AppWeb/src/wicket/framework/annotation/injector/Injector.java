package wicket.framework.annotation.injector;

import engine.java.util.string.TextUtils;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.model.CompoundPropertyModel;

import wicket.framework.annotation.ComponentInjector;
import wicket.framework.annotation.ModelInjector;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 注入机制
 * 
 * @author Daimon
 * @version N
 * @since 6/6/2014
 */
@SuppressWarnings("rawtypes")
public final class Injector {
    
    private static final IInjector NO_INJECTOR = new NoInjector();
    
    private static final HashMap<Class, IInjector> INJECTOR_MAP
    = new HashMap<Class, IInjector>();

    private static IInjector getInjector(Object target) {
        return loadInjector(target.getClass());
    }
    
    private static IInjector loadInjector(Class targetCls) {
        IInjector injector = INJECTOR_MAP.get(targetCls);
        if (injector != null)
        {
            return injector;
        }
        
        String targetName = targetCls.getName();
        if (targetName.startsWith("org.") || targetName.startsWith("java."))
        {
            return NO_INJECTOR;
        }
        
        INJECTOR_MAP.put(targetCls, injector = new ReflectionInjector(targetCls, loadInjector(targetCls.getSuperclass())));
        return injector;
    }

    public static void inject(MarkupContainer target) {
        inject(target, target);
    }
    
    public static void inject(Object target, MarkupContainer container) {
        getInjector(target).inject(target, container);
    }
}

interface IInjector {
    
    /**
     * 注入口（实例化组件）
     */
    void inject(Object target, MarkupContainer container);
}

class NoInjector implements IInjector {

    @Override
    public void inject(Object target, MarkupContainer container) {}
}

class ReflectionInjector extends NoInjector {
    
    private final Class<?> cls;
    private final IInjector parent;
    
    private Field modelField;
    
    private final HashMap<String, ComponentNode> byComponentId = new LinkedHashMap<>();
    private final HashMap<String, ComponentNode> byFieldName = new HashMap<>();
    private final LinkedList<ComponentNode> rootNodeList = new LinkedList<>();
    
    private Field currentInjectField;
    
    public ReflectionInjector(Class<?> cls, IInjector parent) {
        this.cls = cls;
        this.parent = parent;
        init();
    }
    
    private ComponentNode find(String s) {
        ComponentNode node = byComponentId.get(s);
        return node != null ? node : byFieldName.get(s);
    }
    
    private void init() {
        for (Field field : cls.getDeclaredFields())
        {
            if (field.getAnnotation(ModelInjector.class) != null)
            {
                (modelField = field).setAccessible(true);
            }
            else
            {
                ComponentInjector annotation = field.getAnnotation(ComponentInjector.class);
                if (annotation != null)
                {
                    field.setAccessible(true);
                    ComponentNode node = new ComponentNode(field, annotation);
                    byComponentId.put(node.injector.getComponentId(), node);
                    byFieldName.put(field.getName(), node);
                }
            }
        }
        
        treeNode();
    }
    
    /**
     * 整理树形节点
     */
    private void treeNode() {
        if (byComponentId.isEmpty()) return;
        for (ComponentNode node : byComponentId.values())
        {
            String parent = node.annotation.parent();
            if (TextUtils.isEmpty(parent))
            {
                rootNodeList.add(node);
            }
            else
            {
                ComponentNode parentNode = find(parent);
                if (parentNode != null)
                {
                    parentNode.addChild(node);
                }
            }
        }
    }
    
    @Override
    public void inject(Object target, MarkupContainer container) {
        parent.inject(target, container);
        try {
            injectModel(target, container);
            injectComponent(target, container);
        } catch (Exception e) {
            throw new RuntimeException("Inject error--" + currentInjectField, e);
        }
    }
    
    private void injectModel(Object target, MarkupContainer container) throws Exception {
        if (modelField == null) return;
        currentInjectField = modelField;
        Object model = modelField.getType().newInstance();
        
        modelField.set(target, model);
        container.setDefaultModel(new CompoundPropertyModel<>(model));
    }
    
    private void injectComponent(Object target, MarkupContainer container) throws Exception {
        if (rootNodeList.isEmpty()) return;
        for (ComponentNode node : rootNodeList)
        {
            currentInjectField = node.injector.getField();
            node.inject(target, container);
        }
    }
}

class ComponentNode {
    
    final ComponentInjector annotation;
    final ComponentInjectorImpl injector;
    
    public ComponentNode(Field field, ComponentInjector annotation) {
        injector = new ComponentInjectorImpl(field, (this.annotation = annotation).value());
    }
    
    public void inject(Object target, MarkupContainer container) throws Exception {
        Component component = injector.inject(target);
        component.setEscapeModelStrings(annotation.escapeMarkup());
        component.setOutputMarkupId(annotation.outputMarkupId());
        container.queue(component);
        if (children != null)
        {
            container = (MarkupContainer) component;
            for (ComponentNode child : children)
            {
                child.inject(target, container);
            }
        }
    }
    
    List<ComponentNode> children;
    
    public void addChild(ComponentNode child) {
        if (children == null) children = new LinkedList<>();
        children.add(child);
    }
}