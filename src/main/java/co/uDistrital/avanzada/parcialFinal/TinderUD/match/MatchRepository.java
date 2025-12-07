/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.match;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para manejar la tabla matches.
 *
 * @author Alex
 */
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    List<Match> findByIdUsuario1OrIdUsuario2(Long idUsuario1, Long idUsuario2);
    
}
