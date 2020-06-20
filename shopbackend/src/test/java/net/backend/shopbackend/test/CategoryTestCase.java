package net.backend.shopbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.backend.shopbackend.dao.CategoryDAO;
import net.backend.shopbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("net.backend.shopbackend");
		context.refresh();
		categoryDAO =(CategoryDAO)context.getBean("categoryDAO");
	}
	
/*	@Test
	public void testAddCategory(){
		Category c1 = new Category();
		c1.setName("Television1");
		c1.setDescription("This is some decsription of television1");
		c1.setImageURL("CAT_1.jpg");
		c1.setActive(true);		
		assertEquals("successfully added a category inside the table",true,categoryDAO.add(c1));
	}
*/	

	/*@Test
	public void testGetCategory(){
		assertEquals("successfully fetched a single category ","Mobile",categoryDAO.get(3).getName());
	}*/
	
	/*@Test
	public void testUpdateCategory(){
		category = categoryDAO.get(33);
		category.setName("TV");
		assertEquals("successfully updated a category ",true,categoryDAO.update(category));
	}*/
	
	/*@Test
	public void testDeleteCategory(){
		category = categoryDAO.get(1);
		assertEquals("successfully deleted a single category ",true,categoryDAO.delete(category));
	}*/
	
	/*@Test
	public void testListCategory(){
		assertEquals("successfully fetched all active categories ",3,categoryDAO.categories().size());
	}*/
	
	@Test
	public void testCRUDCategory(){

		//Add operation
		category = new Category();
		category.setName("Television");
		category.setDescription("This is some decsription of television");
		category.setImageURL("CAT_1.jpg");
		category.setActive(true);
		assertEquals("successfully added a category inside the table",true,categoryDAO.add(category));
	 	
		category=new Category();
		category.setName("Mobile");
		category.setDescription("This is some decsription of mobile");
		category.setImageURL("CAT_2.jpg");
		category.setActive(true);
		assertEquals("successfully added a category inside the table",true,categoryDAO.add(category));
		
		category=new Category();
		category.setName("Laptop1");
		category.setDescription("This is some decsription of laptop");
		category.setImageURL("CAT_3.jpg");
		category.setActive(true);
		assertEquals("successfully added a category inside the table",true,categoryDAO.add(category));
		
        //Get and Update operation
		category = categoryDAO.get(36);
		category.setName("Laptop");
		assertEquals("successfully updated a category ",true,categoryDAO.update(category));
		
		//Delete operation
		category = categoryDAO.get(36);
		assertEquals("successfully deleted a single category ",true,categoryDAO.delete(category));
		
		//List oeration
		assertEquals("successfully fetched all active categories ",2,categoryDAO.categories().size());
		
		}

}
