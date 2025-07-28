/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.CardDAO;
import poly.quanao.entity.Card;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

/**
 *
 * @author Admin
 */
public class CardDAOImpl implements CardDAO {
    String createSql = "INSERT INTO Cards(Id, Status) VALUES(?, ?)";
    String updateSql = "UPDATE Cards SET Status=? WHERE Id=?";
    String deleteSql = "DELETE FROM Cards WHERE Id=?";
    String findAllSql = "SELECT * FROM Cards";
    String findByIdSql = "SELECT * FROM Cards WHERE Id=?";

    @Override
    public void create(Card entity) {
        Object[] values = {
        entity.getId(),
        entity.getStatus()
        };
        XJdbc.executeUpdate(createSql, values);
    }

    @Override
    public void update(Card entity) {
        Object[] values = {
        entity.getStatus(),
        entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Integer id) {
       XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public Card findById(Integer id) {
        return XQuery.getSingleBean(Card.class, findByIdSql, id);
    }

    @Override
    public List<Card> findAll() {
        return XQuery.getBeanList(Card.class, findAllSql);
    }
    
   
}
