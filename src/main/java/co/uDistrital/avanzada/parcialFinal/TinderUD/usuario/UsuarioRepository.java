/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Permite usar los metodos estander del crud
 * comportamiento de repositorio
 *
 * @author Alex
 */
//Entidad que queremos guardar y el tipo de dato del identificador
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    
    Usuario findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    
     
}
