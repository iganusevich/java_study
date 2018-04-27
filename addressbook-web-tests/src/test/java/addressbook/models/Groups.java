package addressbook.models;

import com.google.common.collect.ForwardingSet;

import java.util.Set;

public class Groups extends ForwardingSet {
    @Override
    protected Set delegate() {
        return null;
    }
}
