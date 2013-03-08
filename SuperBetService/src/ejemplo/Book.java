package ejemplo;

public class Book {
	
 
	    private String isbn;
	    private String title;
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		public String getIsbn() {
			return isbn;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getTitle() {
			return title;
		}
	 
		public String toString(){
			return "book: { isbn : "+isbn+" , title: "+title+" }";
		}


}
