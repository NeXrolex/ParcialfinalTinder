/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Manejo de los datos del usuario
 *
 * @author Alex
 */
@Service // Es un service que se inyecta y comunica
public class UsuarioService {

    @Autowired //Reconoce que se va a inyectar como dependencia
    private UsuarioRepository repositorio;

    @Autowired
    private JavaMailSender mailSender; //para enviar correos

    /**
     * Crea y guarda un usario
     *
     * @param usuario Usuario
     * @return Usuario guardado en el repositorio
     */
    @CrossOrigin//Confirmo que pueda acceder desde otro espacio
    public Usuario crearUsuario(Usuario usuario) {

        return repositorio.save(usuario);

    }

    /**
     * Crear un usuario serializado para trabajarlo en un ambiente html
     *
     * @param usuario Usuario
     * @return Dato exitoso
     */
    @CrossOrigin //Se maneja desde afuera
    //Es importante serialiazar para trabajar con otros entornos
    public ResponseEntity<Usuario> crearUnUsuario(Usuario usuario) {
        
        if (repositorio.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        repositorio.save(usuario);
        return ResponseEntity.ok(usuario);//Puedo realizar el guardado y esta
        //ok para serializarlo

    }

    /**
     * Obtiene todos los usuarios de la base de datos
     *
     * @return Todos los usuarios
     */
    @CrossOrigin
    public List<Usuario> getAllUsuarios() {
        //Trae todos los usuarios de la base de datos
        return repositorio.findAll();

    }

    /**
     * Busqueda especializada por un codigo
     *
     * @param correo Correo a buscar
     * @return Usuario asociado al correo
     */
    @CrossOrigin
    public Usuario findByCorreo(String correo) {

        return repositorio.findByCorreo(correo);

    }

    /**
     * Busca un usario relacionado a un id
     *
     * @param id ID del usuario
     * @return Usuario relacionado al id
     */
    @CrossOrigin
    public Usuario findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * Elimina a un usario por el id
     *
     * @param id Id del isuario
     */
    @CrossOrigin
    public void eliminarUsuario(Long id) {

        repositorio.deleteById(id);
    }
    
    /**
     * Envia un correo a un usario
     * 
     * @param usuario Usuario a enviar el correo
     * @param asunto Asunto del correo
     * @param contenido Contenido del correo
     */
    @CrossOrigin
    public void enviarCorreoRegistro(Usuario usuario, String asunto,
            String contenido) {

        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(usuario.getCorreo());
            mensaje.setSubject(asunto);
            mensaje.setText(contenido);
            mailSender.send(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
