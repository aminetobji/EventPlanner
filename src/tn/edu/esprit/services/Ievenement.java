/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.util.List;

/**
 *
 * @author jasse
 */
public interface Ievenement<E> {
      public void ajout(E e);
      public List<E> getAll();
      public boolean modifier(E e);
      public boolean supprimer(E e);
    
}
