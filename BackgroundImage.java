/**
* Homework 7
* David Lin, dl9bbk
*/

public class BackgroundImage implements Comparable<BackgroundImage>{
	private String imageFileName;
	private String title;
	private String description;
	
	public BackgroundImage() {
		imageFileName = "";
		title = "";
		description = "";
	}
	
	public BackgroundImage(String imageFileName, String title, String description) {
		this.imageFileName = imageFileName;
		this.title = title;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return title + " <" + description + ">";
	}
	
	@Override
	public boolean equals(Object object) {
		//returns if the calling object and the object in parameter are equal
		boolean match = true;
		if(object == null)
			return false;
	
		if(!(object instanceof BackgroundImage))
			return false;
		
		BackgroundImage ob = (BackgroundImage)object;
			
//		if(this.imageFileName == null || this.title == null || this.description == null){
//			if(ob.getImageFileName() != null && this.imageFileName == null || ob.getImageFileName() == null && this.imageFileName != null)
//				match = false;
//			if(ob.getTitle() != null && this.title == null || ob.getTitle() == null && this.title != null)
//				match = false;
//			if(ob.getDescription() != null && this.description == null || ob.getDescription() == null && this.description != null)
//				match = false;
//			return match;
//		}else if(this.imageFileName == null || this.title == null || this.description == null)
//			return false;
		if(this.imageFileName.equals(ob.getImageFileName()) && this.title.equals(ob.getTitle()) && this.description.equals(ob.getDescription()))
			return true;
		else
			return false;
	}
	
	@Override
	public int compareTo(BackgroundImage o) {
		if(o != null && this != null) {
			if(this.imageFileName != null && o.imageFileName != null) {
				int fileNameCompare = this.getImageFileName().compareTo(o.getImageFileName());
				if(fileNameCompare != 0) {
					return fileNameCompare;
				}
			}
			
			if(this.title != null && o.title != null) {
				int titleCompare = this.getTitle().compareTo(o.getTitle());
				if(titleCompare != 0) {
					return titleCompare;
				}
			}
			
			if(this.description != null && o.description != null) {
				int descriptionCompare = this.getDescription().compareTo(o.getDescription());
				if(descriptionCompare != 0) {
					return descriptionCompare;
				}
			}
		}
		
		return 0;
		
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
