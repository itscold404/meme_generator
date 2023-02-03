/**
* Homework 7
* David Lin, dl9bbk
*/

import java.util.Collections;

public class OrderableFeed extends Feed{
	
	public void sortByCaption() {
		Collections.sort(this.getMemes());
	}
	
	public void sortByRating() {
		Collections.sort(this.getMemes(), new CompareMemeByRating());
	}
	
	public void sortByCreator() {
		Collections.sort(this.getMemes(), new CompareMemeByCreator());
	}
}
