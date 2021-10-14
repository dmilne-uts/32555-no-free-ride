package nofreeride.repo;

import nofreeride.model.Review;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class ReviewRepo {

    private Map<Integer, Review> reviewsById ;

    public ReviewRepo() {
        reviewsById = new HashMap<>() ;
    }

    public void save(Review review) {

        this.reviewsById.put(review.getId(), review) ;
    }

    public Review findById(Integer id) {
        return this.reviewsById.get(id) ;
    }

    public Collection<Review> findAll() {
        return this.reviewsById.values();
    }

    public long count() {
        return this.reviewsById.size() ;
    }

}