package mantis.models;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Users extends ForwardingSet<User> {
    private Set<User> delegate;

    public Users(Users contacts) {
        this.delegate = new HashSet<User>(contacts.delegate);
    }

    public Users() {
        this.delegate = new HashSet<User>();
    }

    public Users(Collection<User> users) {
        this.delegate = new HashSet<User>(users);

    }

    @Override
    protected Set<User> delegate() {
        return delegate;
    }

    public Users  withAdded (User contact) {
        Users contacts = new Users(this);
        contacts.add(contact);
        return contacts;
    }

    public Users  without (User contact) {
        Users contacts = new Users(this);
        contacts.remove(contact);
        return contacts;
    }

    public Users  withModified (User modifiedContact, User contact) {
        Users contacts = new Users(this);
        return contacts.without(modifiedContact).withAdded(contact);
    }
}
