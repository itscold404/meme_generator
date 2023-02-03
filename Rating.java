
/**
* Homework 7
* David Lin, dl9bbk
*/

public class Rating {
	private int score;
	private User user;
	
	
	public Rating() {
		user = new User();
		//score = 0;
	}

	public Rating(User user, int score) {
		this.score = score;
		this.user = user;
	}
	
	public Rating(int score, User user) {
		this.score = score;
		this.user = user;
	}
	
	@Override
	public String toString() {
		String temp = "";
		switch(score) {
			case 1:
				temp = user.getUserName() + " rated as an upvote";
				break;
			case -1:
				temp = user.getUserName() + " rated as a downvote";
				break;
			case 0:
				temp = user.getUserName() + " rated as a pass";
				break;
			default:
				temp = user.getUserName() + " rated as a pass";
		}
		return temp;
	}
	
	@Override
	public boolean equals(Object object) {
		//returns if the calling object and the object in parameter are equal
		if(object == null)
			return false;
		
		if(!(object instanceof Rating))
			return false;
		
		Rating ob = (Rating)object;
		
		if(this.score == ob.getScore() && this.user == null){
			if(ob.getUser() == null)
				return true;
			else
				return false;
		}
		
		if(this.user != null) {
			if(this.user.equals(ob.getUser()) && this.score == ob.getScore())
				return true;
		}
		
		return false;
	}

	public int getScore() {
		return score;
	}

	public boolean setScore(int score) {
		boolean reached = false;
		switch(score) {
		case 1:
			this.score = 1;
			reached = true;
			break;
		case -1:
			this.score = -1;
			reached = true;
			break;
		case 0: 
			this.score = 0;
			reached = true;
			break;
		default:
			reached = false;
		}
		return reached;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
