/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data //De lombok, de esta manera pudiendo usar los get y los set
@Entity //Le mencionamos que va a ser una tabla
@Table(name = "usuarios") //Nombre de la tabla
public class Usuario {

    @Id//Identificador de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*De esta manera asignamos
    el id y que no sea de manera manual*/
    private Long id;
        
    @NotBlank(message = "El nickname no puede estar vacio")
    @Size(min = 3, max = 50)
    private String nickname;
    private String nombre;
    private String apellido;
    
    @NotBlank
    @Email
    private String correo;
    @NotBlank
    private String password;
    @NotBlank
    @Min(18)
    @Max(120)
    private Integer edad;
    private String ciudad;
    private String fechaNacimiento;
    private String generoUsuario;
    private String generoInteres;
    private int edadMin;
    private int edadMax;
    private String distanciaMax;

    /**
     * Constructor vacio
     *
     */
    public Usuario() {
    }

    /*En el constructor no entra el id porque es el identificador de la tabla
    entonces no puede estar dentro del objeto*/
    /**
     * Constructor con los parametros de un usario en tinder
     * 
     *@param nickname Apodo del usuario
     * @param nombre Nombre del Usuario
     * @param apellido Apellido del Usuario
     * @param correo Correo electronico del usuario
     * @param password Contrasena del usuario
     * @param edad Edad del usuario
     * @param ciudad Ciudad del usuario
     * @param fechaNacimiento Fecha de nacimiento del usuario
     * @param generoUsuario Genero del usuario
     * @param generoInteres Genero de interes del usuario
     * @param edadMin Edad minima de preferencias del usuario
     * @param edadMax Edad maxima de preferencias del usuario
     * @param distanciaMax Distancia maxima de preferencia del usuario
     */
    public Usuario(String nickname,String nombre, String apellido, String correo,
            String password,Integer edad, String ciudad, String fechaNacimiento,
            String generoUsuario, String generoInteres, int edadMin, int edadMax,
            String distanciaMax) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.edad  = edad;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.generoUsuario = generoUsuario;
        this.generoInteres = generoInteres;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.distanciaMax = distanciaMax;
    }

}
