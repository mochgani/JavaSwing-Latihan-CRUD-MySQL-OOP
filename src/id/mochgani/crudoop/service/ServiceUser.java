/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.mochgani.crudoop.service;

import id.mochgani.crudoop.entity.User;
import java.util.List;

/**
 *
 * @author mochgani
 */
public interface ServiceUser {
    void insertUser(User usr);
    void updateUser(User usr);
    void deleteUser(User usr);
    User getByid(String id);
    List<User> getUser();
    Boolean getLogin(User usr);
    User getLoginData(User usr);
}
