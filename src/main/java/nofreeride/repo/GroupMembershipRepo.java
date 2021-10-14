package nofreeride.repo;

import nofreeride.model.GroupMembership;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMembershipRepo extends CrudRepository<GroupMembership, Integer> {

    List<GroupMembership> findByStudentId(int studentId) ;

    List<GroupMembership> findByGroupId(int groupId) ;

    GroupMembership findByStudentIdAndGroupId(int studentId, int groupId) ;

}
