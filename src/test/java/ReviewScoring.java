import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nofreeride.model.Review;
import nofreeride.model.reviewing.*;

class ReviewScoring {

	
	@Test
	void testTerribleReview() {
		
		Review review = new Review(1,1,1);
		review.setDetails(
				CommunicationAmount.none, 
				LowCommunicationReason.difficultyWithGroupmate,
				null,
				ContributionAmount.none,
				null
				) ;
		
		assertEquals(review.getScore(), 0);
	}
	
}
