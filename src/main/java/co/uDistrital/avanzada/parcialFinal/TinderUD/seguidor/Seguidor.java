/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.seguidor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Representa la entidad de un seguidor 
 * en tinder
 *
 * @author Alex
 */
@Data
@Entity
@Table(name = "Seguidores")
public class Seguidor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idSeguidor;
    private Long idSeguido;
    
    /**
     * Constructor vacio 
     * 
     */
    public Seguidor() {
    }
    
    /**
     * Asigna valores comunes en un seguidor
     * 
     * @param idSeguidor Id  del seguidor
     * @param idSeguido Id de quien lo sigue
     */
    public Seguidor(Long idSeguidor, Long idSeguido) {
        this.idSeguidor = idSeguidor;
        this.idSeguido = idSeguido;
    }
    
    
            
    
}
