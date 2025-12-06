/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.swipe;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio jpa para manejar la tabla de
 * swipes
 *
 * @author Alex
 */
public interface SwapeRepository extends JpaRepository<Swipe, Long>{
    
    //Swipes hechos por un usuario
    List<Swipe> findByIdEmisor(Long idEmisor);
    
    //Swipes recibidos por un usuario
    List<Swipe> findByIdReceptor(Long idReceptor);
}
