/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.services;

import edu.event.entities.User;
import java.util.List;

/**
 *
 * @author Amine
 * @param <User>
 */
public interface IUser<User> {

    public void ajouter(User p);

    public void supprimer(int id);

    public void modifier(User p);

    public List<User> getAll();

    public User getOneById(int id);

    public Boolean VerifierMailPaswd(String psw, String mail);

    public int getNbrClientActif();

    public int getNbrCLient();

    public Boolean SiADMIN(int idUser);

    public Boolean SiEVENTMANAGER(int idUser);

    public int getNbrEventManagerActif();

    public int getNbrEventManager();

    public int getNbrEventManagerNoActif();
}
