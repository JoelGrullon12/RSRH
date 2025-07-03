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
public interface IService<Entity> extends IReadOnlyService<Entity> {
    boolean insert(Entity e);
    boolean update(Entity e);
    boolean delete(Entity e);
    boolean delete(int id);
}
