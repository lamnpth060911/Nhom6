/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.quanao.ui.manager;

import poly.quanao.entity.Products;

/**
 *
 * @author Admin
 */
public interface ProductsManagerController extends CrudController <Products> {
    void fillCategories();
    void chooseFile();
}
