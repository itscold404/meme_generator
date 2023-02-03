
/**
* Homework 7
* David Lin, dl9bbk
*/

public class Meme implements Comparable<Meme>{
	private User creator;
	private BackgroundImage backgroundImage;
	private Rating[] ratings; 
	private String caption;
	private String captionVerticalAlign; 
	private boolean shared;
	private int plus1;
	private int minus1;
	
	public Meme() { 
		backgroundImage = new BackgroundImage();
		creator = new User();
		caption = "";
		ratings = new Rating[10];
		captionVerticalAlign = "bottom";
	}
	
	public Meme(BackgroundImage backgroundImage, String caption, User creator) { 
		this.backgroundImage = backgroundImage;
		this.caption = caption;
		this.creator = creator;
		this.ratings = new Rating[10];
		this.captionVerticalAlign = "bottom";
	}
	
	public boolean addRating (Rating rating) {   
		//adds Rating object to array of Rating
		int count = 0;
		Rating[] temp = new Rating[10];
		for(int i = 0; i < ratings.length; i++) {
			if(ratings[i] != null) {
				count++;
				temp[i] = ratings[i];
			}

		}
		if(count > 9) {
			for(int i = 0; i < ratings.length-1; i++) {
				if(ratings[9-i] != null)
					temp[9-i-1] = ratings[9-i];
			}
			temp[9] = rating;
		}

		if(count <=9)
			temp[count] = rating;
		ratings = temp;
		return true;
	}
	
	public double calculateOverallRating() {
		//adds up all the ratings
		double total = 0.0;
		
		for(int i = 0; i < ratings.length; i++) {
			if(ratings[i] != null) {
				double temp = ratings[i].getScore();
				
				if(Double.compare(temp, 1) == 0)
					plus1++;
				
				if(Double.compare(temp, -1) == 0)
					minus1++;
				
				total += temp;
			}
		}
		return total;
	}
	@Override
	public String toString(){
		return this.backgroundImage + " '" + caption + "' " + calculateOverallRating() 
		+ " [+1: " + plus1 + ", -1: " + minus1 + "]" + " - created by " + this.creator.getUserName(); 		
	}
	
	@Override
	public boolean equals (Object object) { 
		//returns if the calling object and the object in parameter are equal
		boolean match = true;
		if(object == null)
			return false;
		
		if(!(object instanceof Meme))
			return false;
		
		Meme ob = (Meme)object;
//		if(this.creator == null || this.caption == null || this.backgroundImage == null){
//			if(ob.getCreator() != null && this.creator == null || ob.getCreator() == null && this.creator != null)
//				match = false;
//			if(ob.getCaption() != null && this.caption == null || ob.getCaption() == null && this.caption != null)
//				match = false;
//			if(ob.getBackgroundImage() != null && this.backgroundImage == null || ob.getBackgroundImage() == null && this.backgroundImage != null)
//				match = false;
//		
//			return match;
//		}else if(this.creator != null && this.caption != null && this.backgroundImage != null 
//				&& ob.getCreator() != null && ob.getCaption() != null && ob.getBackgroundImage() != null){
//			if(this.creator.getUserName() == null && ob.getCreator().getUserName() ==  null && this.backgroundImage.equals(ob.getBackgroundImage()) && this.caption.equals(ob.getCaption()))
//					return true;
		
		if(this.creator.equals(ob.getCreator()) && this.backgroundImage.equals(ob.getBackgroundImage()) && this.caption.equals(ob.getCaption()))
			return true;
			
		return false;
	}
	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}
	
	public void setBackgroundImage(BackgroundImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	
	public Rating[] getRatings() {
		return ratings;
	}
	
	public void setRatings(Rating[] rating) {
		this.ratings = rating;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getCaptionVerticalAlign() {
		return captionVerticalAlign;
	}
	
	public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
		switch(captionVerticalAlign) {
		case "top":
			this.captionVerticalAlign = "top";
			return true;
		
		case "middle":
			this.captionVerticalAlign = "middle";
			return true;
			
		case "bottom":
			this.captionVerticalAlign = "bottom";
			return true;
			
		default:
			return false;
		}
	}
	
	public boolean getShared() {
		return shared;
	}
	public void setShared(boolean shared) {
		this.shared = shared;
	}

	@Override
	public int compareTo(Meme m) {
		if(m != null && this != null) {
			if(this.caption != null && m.caption != null) {
				int captionCompare = this.caption.compareTo(m.caption);
				if(captionCompare != 0)
					return captionCompare;
			}
			
			if(m.backgroundImage != null){
				if(this.backgroundImage.compareTo(m.backgroundImage) != 0)
					return this.backgroundImage.compareTo(m.backgroundImage);
			}
			
			if((m.calculateOverallRating() - this.calculateOverallRating()) != 0)
				return (int)(m.calculateOverallRating() - this.calculateOverallRating());
			
			Boolean b = (Boolean)(m.shared);
			if(b.compareTo(this.shared) != 0)
				return b.compareTo(this.shared);
		}
		
		return 0;
	}


}
