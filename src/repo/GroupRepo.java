package repo;

import model.Group;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroupRepo {

    private Map<Integer,Group> groupsById ;

    public GroupRepo() {
        groupsById = new HashMap<>() ;
    }

    public void save(Group group) {
        this.groupsById.put(group.getId(), group) ;
    }

    public Group getById(Integer id) {
        return this.groupsById.get(id) ;
    }

    public Collection<Group> getAll() {
        return this.groupsById.values();
    }

    public int getSize() {
        return this.groupsById.size() ;
    }
}


