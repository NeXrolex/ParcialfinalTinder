/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.foto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
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
    private String descripcion;  
    private boolean principal;  //Indica si es la foto principal del perfil
    @Temporal(TemporalType.TIMESTAMP)
    //Fecha y hora de publicacion de la foto
    private Date fechaPublicacion;
    
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
     * @param descripcion Descripcion de la foto
     * @param principal Indica si es la foto principal
     * @param fechaPublicacion Fecha y hora de publicacion
     */
    public Foto(Long idUsuario, String url, String descripcion,
            boolean principal, Date fechaPublicacion) {
        this.idUsuario = idUsuario;
        this.url = url;
        this.descripcion = descripcion;
        this.principal = principal;
        this.fechaPublicacion = fechaPublicacion;
    }
    
    
    
}
