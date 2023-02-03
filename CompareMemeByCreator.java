/**
* Homework 7
* David Lin, dl9bbk
*/

import java.util.Comparator;

public class CompareMemeByCreator implements Comparator<Meme>{

	@Override
	public int compare(Meme o1, Meme o2) {
		if(o1 != null && o2 != null) {
			if(o1.getCreator().compareTo(o2.getCreator()) != 0)
				return o1.getCreator().compareTo(o2.getCreator());
			
			if(o2.calculateOverallRating() - o1.calculateOverallRating() != 0)
				return (int)(o2.calculateOverallRating() - o1.calculateOverallRating());
			
			if(o1.getCaption().compareTo(o2.getCaption()) != 0)
				return o1.getCaption().compareTo(o2.getCaption());
			
			if(o1.getBackgroundImage().compareTo(o2.getBackgroundImage()) != 0)
				return o1.getBackgroundImage().compareTo(o2.getBackgroundImage());
			}
			
			Boolean second = (Boolean)o2.getShared();
			if(second.compareTo(o1.getShared()) != 0)
				return second.compareTo(o1.getShared());
	
		return 0;
	}
}
