/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.services;

import edu.event.entities.Event;
import java.sql.SQLException;
import java.util.List;

public interface IEvent {

    public boolean add(Event e);

    public List<Event> getAll();

    public Event getEventById(int id);

    public boolean modify(Event e);

    public boolean delete(Event e);

}
