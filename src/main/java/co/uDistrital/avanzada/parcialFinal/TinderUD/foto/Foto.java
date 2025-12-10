/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.foto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Representa una foto de un usuario en la aplicacion TinderUD
 *
 * @author Alex
 */
@Data
@Entity
@Table(name = "fotos")
public class Foto {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;   
    private String url; //Ruta, nombre de archivo o URL de la foto
    private int orden;  
    
    /**
     * Constructor vacio
     * 
     */
    public Foto() {
    }
    
    /**
     * Constructor con parametros para crear una foto de manera directa.
     * 
     * @param idUsuario Id del usuario dueño de la foto
     * @param url Ruta o nombre del archivo/URL de la foto
     * @param orden Orden de las fotos
     */
    public Foto(Long idUsuario, String url, int orden) {
        this.idUsuario = idUsuario;
        this.url = url;
        this.orden = orden;
    }
}
