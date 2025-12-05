/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Manejo de los datos del usuario
 *
 * @author Alex
 */
public class UsuarioService {
    
    @Autowired //Reconoce que se va a inyectar como dependencia
    private UsuarioRepository repositorio;
    
    /**
     * Crea y guarda un usario
     * 
     * @param usuario Usuario
     * @return Usuario guardado en el repositorio
     */
    @CrossOrigin//Confirmo que pueda acceder desde otro espacio
    public Usuario crearUsuario(Usuario usuario){
        
        return repositorio.save(usuario);
        
    }
    /**
     * Crear un usuario serializado para trabajarlo
     * en un ambiente html
     * 
     * @param usuario Usuario
     * @return Dato exitoso 
     */
    @CrossOrigin //Se maneja desde afuera
    //Es importante serialiazar para trabajar con otros entornos
    public ResponseEntity<Usuario> crearUnUsuario(Usuario usuario){
        
        repositorio.save(usuario);
        return ResponseEntity.ok(usuario);//Puedo realizar el guardado y esta
        //ok para serializarlo
        
    }
    /**
     * Obtiene todos los usuarios de la base de datos
     * 
     * @return Todos los usuarios
     */
    @CrossOrigin
    public List<Usuario> getAllUsuarios(){
        //Trae todos los usuarios de la base de datos
        return repositorio.findAll();
        
    }
    
    /**
     * Busqueda especializada por un codigo
     * 
     * @param codigo Codigo a buscar
     * @return Usuario asociado al codigo
     */
    @CrossOrigin
    public Usuario findByCodigo(String codigo){
        
        return repositorio.findByCodigo(codigo);
        
    }
}
