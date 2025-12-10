/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.match;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * representa un math entre dos jugadores
 * un match se genera cuando dos usuarios de likean
 *
 * @author Alex
 */
@Data //getterand setter
@Entity
@Table(name = "matches")
public class Match {
    
    @Id                                                
    @GeneratedValue(strategy = GenerationType.IDENTITY)//El ID se genera automaticamente en la BD
    private Long id;
    
    //Id del primer usuario del match
    private Long idUsuario1;
    
    //Id del segundo usuario del match
    private Long idUsuario2;
    
    //Estado del match (por ejemplo: ACTIVO, BLOQUEADO, ELIMINADO)
    private String estado;
    
    /**
     * Constructor vacio 
     */
    public Match() {
    }
    
    /**
     * Constructor con parametros para crear un match de manera directa.
     * 
     * @param idUsuario1 Id del primer usuario
     * @param idUsuario2 Id del segundo usuario
     * @param fechaHora Fecha y hora del match
     * @param estado Estado actual del match
     */
    public Match(Long idUsuario1, Long idUsuario2, String estado) {
        this.idUsuario1 = idUsuario1;
        this.idUsuario2 = idUsuario2;
        this.estado = estado;
    }
    
}
