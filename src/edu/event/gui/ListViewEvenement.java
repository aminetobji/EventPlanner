/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.Event;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewEvenement extends ListCell<Event> {

    @Override
    public void updateItem(Event e, boolean empty) {
        super.updateItem(e, empty);
        if (e != null) {

            EvenementItemController data = new EvenementItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }

}
