/**
* Homework 7
* David Lin, dl9bbk
*/

import java. util. ArrayList;
import java.util.TreeSet;

/**
* Homework 6
* David Lin, dl9bbk
*/

public class User implements Comparable<User>{
	private String userName;
	private ArrayList<Meme> memesCreated;
	private TreeSet<Meme> memesViewed;

	public User() {
		userName = "genericName";
		memesCreated = new ArrayList<Meme>();
		memesViewed = new TreeSet<Meme>();
	}
	
	public User(String userName) {
		this.userName = userName;
		memesCreated = new ArrayList<Meme>();
		memesViewed = new TreeSet<Meme>();
	}
	
	public void rateMeme(Meme meme, int rating){
		Rating r = new Rating(new User(this.userName), rating);
		meme.addRating(r);
		memesViewed.add(meme);
	}
	
	public Meme createMeme(BackgroundImage image, String caption) {
		Meme newMeme = new Meme(image, caption, this);
		memesCreated.add(newMeme);
		return newMeme;
	}
	
	public boolean deleteMeme(Meme meme) {
		for(int i = 0; i < memesCreated.size(); i++)
		{
			if(memesCreated.get(i) != null && meme.equals(memesCreated.get(i)) && 
					memesCreated.get(i).getShared() == false) {
				memesCreated.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void shareMeme(Meme meme, Feed feed) {
		for(int i = 0; i < memesCreated.size(); i++) {
			if(memesCreated.get(i).equals(meme)) {
				memesCreated.get(i).setShared(true);
			}
		}
		feed.getMemes().add(meme);     
	}
	
	public boolean rateNextMemeFromFeed (Feed feed, int ratingScore) {
		if(feed.getNewMeme(this) != null) {      //if this doesnt work have a user as private object in feed
			Meme meme = feed.getNewMeme(this);	 // and create user in getNewMeme() and set reference so that getUser() can be used
			this.rateMeme(meme, ratingScore);    // to get user for this method
			return true;
		}
		
		return false;
	}
	
	public double calculateReputation () {
		double reputation = 0.0;
		int count_memes = 0;
		for(Meme m: memesCreated) {
			reputation += m.calculateOverallRating();
			count_memes++;
		}
		
		if(count_memes == 0)
			return reputation;
		else
			return reputation/count_memes;
	}
	
	@Override
	public String toString() {
		double temp = calculateReputation() * 10;
		return this.userName + " has rated (" + memesViewed.size() + ") memes, (" + Math.round(temp)/(double)10 + ")";
	}
	
	@Override
	public boolean equals(Object object) {
		if(object == null)
			return false;
		if(!(object instanceof User))
			return false;
		User ob = (User)object;
		
		if(this.userName.equals(ob.getUserName()))
			return true;
		else
			return false;
	} 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Meme> getMemesCreated() {
		return memesCreated;
	}

	public void setMemesCreated(ArrayList<Meme> memesCreated) {
		this.memesCreated = memesCreated;
	}

	public TreeSet<Meme> getMemesViewed() {
		return memesViewed;
	}

	public void setMemesViewed(TreeSet<Meme> memesViewed) {
		this.memesViewed = memesViewed;
	}

	@Override
	public int compareTo(User u) {
		
		if(this.userName.compareTo(u.userName) != 0)
			return this.userName.compareTo(u.userName);
		
		if(u.memesCreated.size() - this.memesCreated.size() != 0)
			return u.memesCreated.size() - this.memesCreated.size();
		
		return 0;
	}
}
