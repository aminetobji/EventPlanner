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
public interface Icategorie<C>{
      public void ajout(C c);
      public List<C> getAll();
      public boolean modifier(C c);
      public boolean supprimer(C c);
    
}
