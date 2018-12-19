package models;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class ClassSet extends ForwardingSet {

    private Set<Class> delegate = new HashSet<>();

    public ClassSet(ClassSet classSet) {
        this.delegate = new HashSet<Class>(classSet.delegate());
    }

    public ClassSet() {

    }

    @Override
    protected Set delegate() {
        return delegate;
    }

    public ClassSet withClass(Class new_class){
        this.add(new_class);
        return this;
    }

    public ClassSet withClasses(ClassSet new_classes){
        this.add(new_classes);
        return this;
    }

    public ClassSet withoutClass(Class del_class){
        this.remove(del_class);
        return this;
    }

    public ClassSet withoutClasses(ClassSet del_classes){
        this.remove(del_classes);
        return this;
    }
}
