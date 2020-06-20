package net.backend.shopbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.backend.shopbackend.dao.CategoryDAO;
import net.backend.shopbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

@Autowired
private SessionFactory sessionFactory;
	
/*
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
	}*/
	
	@Override
	public List<Category> categories() {
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}
	
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class,Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {
		try{
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}

	@Override
	public boolean update(Category category) {
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}

	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}

}
