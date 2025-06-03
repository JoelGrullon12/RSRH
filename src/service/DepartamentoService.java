/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import data.repositories.GenericRepository;
import java.sql.SQLException;
import java.util.List;
import model.BaseEntity;
import model.Departamento;
import model.Idioma;

/**
 *
 * @author Joel Grullon
 */
public class DepartamentoService {
    
     private UnitOfWork _uow;
     private GenericRepository<Departamento> _repo;

     
     public DepartamentoService() throws SQLException{
         _uow=new UnitOfWork();
         _repo=_uow.getRepository(Departamento.class);
     }
     
     public List<Departamento> getAll(){
         return _repo.getAll();
     }
     
     public Departamento findById(int id){
         return _repo.findById(id);
     }
     
     public boolean insert(Departamento departamento){
         try{
            boolean result= _repo.insert(departamento);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean update(Departamento departamento){
         try{
            boolean result= _repo.update(departamento);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean delete(Departamento departamento){
         try{
            boolean result= _repo.delete(departamento);
            _uow.save();
            return result;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
     }
}
