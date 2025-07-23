/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.quanao.dao;

import java.util.List;
import poly.quanao.entity.Products;

/**
 *
 * @author ADMIN
 */

public interface ProductsDAO extends CrudDAO<Products, Integer> {
    List<Products> findByCategoryId(int categoryId);
}



