package nofreeride.repo;

import nofreeride.model.Review;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ReviewRepo extends CrudRepository<Review, Integer> {

    List<Review> findByReviewerId(Integer id) ;
    
    List<Review> findByRevieweeId(Integer id) ;

}