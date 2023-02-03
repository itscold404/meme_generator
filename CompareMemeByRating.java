/**
* Homework 7
* David Lin, dl9bbk
*/

import java.util.Comparator;

public class CompareMemeByRating implements Comparator<Meme>{

	@Override
	public int compare(Meme m1, Meme m2) {
		if(m1 != null && m2 != null) {
			if(m2.calculateOverallRating() - m1.calculateOverallRating() != 0)
				return (int)(m2.calculateOverallRating() - m1.calculateOverallRating());
			
			if(m1.getCaption().compareTo(m2.getCaption()) != 0)
				return m1.getCaption().compareTo(m2.getCaption());
			
			if(m1.getBackgroundImage().compareTo(m2.getBackgroundImage()) != 0)
				return m1.getBackgroundImage().compareTo(m2.getBackgroundImage());
			
			if(m1.getCreator().compareTo(m2.getCreator()) != 0)
				return m1.getCreator().compareTo(m2.getCreator());
		} 
		
		return 0;
	}
	
}
