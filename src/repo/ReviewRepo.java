package repo;

import model.Review;

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

    public Review getById(Integer id) {
        return this.reviewsById.get(id) ;
    }

    public Collection<Review> getAll() {
        return this.reviewsById.values();
    }

    public int getSize() {
        return this.reviewsById.size() ;
    }

}
