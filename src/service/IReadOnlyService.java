/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author Joel Grullon
 */
public interface IReadOnlyService<Entity> {
    List<Entity> getAll();
    Entity findById(int id);
}
