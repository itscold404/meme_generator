/**
* Homework 7
* David Lin, dl9bbk
*/

import java. util. ArrayList;

/**
* Homework 3
* David Lin, dl9bbk
*/

public class Feed {
	private ArrayList<Meme> memes;
	//private User user;
	
	public Feed() {
		memes = new ArrayList<Meme>();
	};

	
	public Meme getNewMeme(User user) {
		//this.user = user;
		for(Meme m: memes) {
			if(m != null && !(user.getMemesViewed().contains(m)) && !(m.getCreator().equals(user))){
				return m;
			}
		}	
		return null;
	}
	
	public ArrayList<Meme> getMemes() {
		return memes;
	}
	public void setMemes(ArrayList<Meme> memes) {
		this.memes = memes;
	}
	
	
	public String toString() {
		String fin = "";
		
		for(Meme m: memes) {
			fin += m.toString() + "\n"; 
		}
		
		return fin;
	}
}
