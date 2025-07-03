/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import model.Departamento;
import model.Empleado;
import model.Usuario;
import service.DepartamentoService;
import service.EmpleadoService;
import service.UsuarioService;

/**
 *
 * @author Joel Grullon
 */
public class SeedBD {
    public static void SeedUsuario(){
        
        DepartamentoService depService=new DepartamentoService();
        Departamento dep=depService.findByNombre("TI");
        
        if(dep==null){
            Departamento nuevoDep=new Departamento();
            nuevoDep.setNombreDepartamento("TI");
            depService.insert(nuevoDep);
            dep=depService.findByNombre("TI");
        }
        
        EmpleadoService empServicio=new EmpleadoService();
        Empleado empleado=empServicio.findByCedula("00123456789");
        
        if(empleado==null){
            Empleado nuevoEmpleado=new Empleado();
            nuevoEmpleado.setCedula("00123456789");
            nuevoEmpleado.setNombreEmpleado("Administrador");
            nuevoEmpleado.setApellidoEmpleado("Sistema");
            nuevoEmpleado.setDepartamentoId(dep.getIdDepartamento());
            empServicio.insert(nuevoEmpleado);
            
            empleado=empServicio.findByCedula("00123456789");
        }
        
        UsuarioService usuServicio=new UsuarioService();
        Usuario usuario=usuServicio.findByUsername("admin");
        
        if(usuario == null){
            usuario=new Usuario();
            usuario.setNombreUsuario("admin");
            usuario.setContrasenia("Admin123");
            usuario.setTipoUsuario("EMPLEADO");
            usuario.setEmpleadoId(empleado.getIdEmpleado());
            usuServicio.insert(usuario);
        }
        
    }
}
