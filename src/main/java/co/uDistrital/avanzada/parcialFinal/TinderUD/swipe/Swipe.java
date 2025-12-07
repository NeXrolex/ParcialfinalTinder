/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.swipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

/**
 * Representa las interacciones de tipo swipe 
 * entre dos usuarios
 *
 * @author Alex
 */
@Data//Getter and setter
@Entity
@Table(name = "swipes")
public class Swipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idEmisor;
    private Long idReceptor;
    private Long tipo; //Tipo de interaccion tipo like, superLike, dislike
    private Date fechaHora;
    
    /**
     * Constructor vacio
     * 
     */
    public Swipe() {
    }
    
    /**
     * Constructor con parametros de la interaccion entre
     * los dos usuarios
     * 
     * @param idEmisor ID del usuario que realiza el swipe
     * @param idReceptor Id del usuario que recibe el swipe
     * @param tipo Tipo de swipe
     * @param fechaHora Fecha y hora del swipe
     */
    public Swipe(Long idEmisor, Long idReceptor, Long tipo, Date fechaHora) {
        this.idEmisor = idEmisor;
        this.idReceptor = idReceptor;
        this.tipo = tipo;
        this.fechaHora = fechaHora;
    }
    
    
    
    
    
}
