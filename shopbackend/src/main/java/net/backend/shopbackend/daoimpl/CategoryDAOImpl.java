package net.backend.shopbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.backend.shopbackend.dao.CategoryDAO;
import net.backend.shopbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category>	categories = new ArrayList<>();
	static{
		Category c1=new Category();
		c1.setId(1);
		c1.setName("Television");
		c1.setDescription("This is some decsription of television");
		c1.setImageURL("CAT_1.jpg");
		c1.setActive(true);
		
		Category c2=new Category();
		c2.setId(2);
		c2.setName("Mobile");
		c2.setDescription("This is some decsription of mobile");
		c2.setImageURL("CAT_2.jpg");
		c2.setActive(true);
		
		Category c3=new Category();
		c3.setId(3);
		c3.setName("Laptop");
		c3.setDescription("This is some decsription of laptop");
		c3.setImageURL("CAT_3.jpg");
		c3.setActive(true);
		
		categories.add(c1);
		categories.add(c2);
		categories.add(c3);
	}
	
	@Override
	public List<Category> categories() {
		
		return categories;
	}
	
	public Category get(int id) {
	
		for(Category category : categories){
			if(category.getId() == id){	
				return category;
			}
		}
		return null;
	}

}
