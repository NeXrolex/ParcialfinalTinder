/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.foto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para manejar la tabla fotos.
 *
 * @author Alex
 */
public interface FotoRepository extends JpaRepository<Foto, Long> {
    
    List<Foto> findByIdUsuario(Long idUsuario);
    
}
