package net.backend.shopbackend.dao;

import java.util.List;

import net.backend.shopbackend.dto.Category;

public interface CategoryDAO {

	List<Category> categories();
	Category get(int id);
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}
