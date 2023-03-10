/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.services;

import edu.event.entities.Category;
import java.sql.SQLException;
import java.util.List;

public interface ICategory {

    public boolean add(Category c);

    public List<Category> getAll();

    public Category getById(int id);

    public boolean modify(Category c);

    public boolean delete(Category c);

}
