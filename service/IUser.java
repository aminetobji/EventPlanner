/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfinal.service;

import java.util.List;

/**
 *
 * @author Cardinal
 */
public interface IUser {
    public boolean checkUserById(int id);
    public List<String> getAllEmail();
}
