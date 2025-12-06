/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.seguidor;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio del seguidor, maneja la tabla de seguidores
 *
 * @author Alex
 */
public interface SeguidorRepository extends JpaRepository<Seguidor, Long> {
    
    //Lista de usuarios a los que sigue un usuario
    List<Seguidor> findByIdSeguidor(Long idSeguidor);
    
    //Lista de usuarios que siguen a un usuario
    List<Seguidor> findByIdSeguido(Long idSeguido);
    
}
