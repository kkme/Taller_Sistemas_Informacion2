package ejemplo;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/library")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class Library {
 
    @GET
    @Path("/books")
    public Collection<Book> getBooks() {
    	Collection<Book> books = new ArrayList<Book>();
    	
    	Book b1 = new Book();
    	b1.setIsbn("is:ben:id");
    	b1.setTitle("La virgen del agua negra");
    	
    	Book b2 = new Book();
    	b2.setIsbn("is:ben:id");
    	b2.setTitle("Brigada Cola");
    	
    	Book b3 = new Book();
    	b3.setIsbn("is:ben:id");
    	b3.setTitle("Gomazo subete");
    	
    	books.add(b1);
    	books.add(b2);
    	books.add(b3);
    	
		return books;
    }
 
    @GET
    @Path("/book/{isbn}")
    public Book getBook(@PathParam("isbn") String id) {
    	Book b3 = new Book();
    	b3.setIsbn(id);
    	b3.setTitle("Gomazo subete");
    	
		return b3;
    
    }
 
    @PUT
    @Path("/book/{isbn}")
    public Book addBook(@PathParam("isbn") String id, @QueryParam("title") String title) {
    	Book b3 = new Book();
    	b3.setIsbn(id);
    	b3.setTitle(title);
    	return b3;
    
    }
 
    @POST
    @Path("/book/{isbn}")
    public Book updateBook(@PathParam("isbn") String id, String title) {
    	Book b3 = new Book();
    	b3.setIsbn(id);
    	b3.setTitle(title);
		return b3;
    
    }
 
    @DELETE
    @Path("/book/{isbn}")
    public Book removeBook(@PathParam("isbn") String id) {
    	Book b3 = new Book();
    	b3.setIsbn(id);
    	b3.setTitle("Gomazo subete");
		return b3;
    }
}