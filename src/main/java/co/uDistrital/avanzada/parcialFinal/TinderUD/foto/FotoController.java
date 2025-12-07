/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.uDistrital.avanzada.parcialFinal.TinderUD.foto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Maneja los endpoints relacionados a las fotos del usuario.
 *
 * @author Alex
 */
@RestController
public class FotoController {

    @Autowired
    private FotoService service;

    /**
     * Crea una nueva foto. Recibe un objeto Foto en formato JSON y retorna la
     * foto creada.
     *
     * @param foto Objeto Foto enviado en el cuerpo de la peticion
     * @return Foto creada dentro de un ResponseEntity
     */
    @RequestMapping(value = "/api/foto", method = RequestMethod.POST)
    @CrossOrigin(origins = "*") //Permite el acceso desde el frontend en este puerto
    public ResponseEntity<Foto> crearFoto(@RequestBody Foto foto) {
        Foto guardada = service.guardar(foto);
        return ResponseEntity.ok(guardada);
    }

    @PostMapping(value = "/api/foto/subir/{idUsuario}", consumes = "multipart/form-data")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Foto>> subirFotos(
            @PathVariable Long idUsuario,
            @RequestParam("fotos") List<MultipartFile> archivos,
            @RequestParam("orden") List<Integer> ordenes
    ) {
        List<Foto> guardadas = new ArrayList<>();

        String carpetaUploads = "src/main/resources/uploads/"; // <-- ruta donde guardarás

        for (int i = 0; i < archivos.size(); i++) {
            MultipartFile archivo = archivos.get(i);
            int orden = ordenes.get(i);

            String nombre = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();

            Path ruta = Paths.get(carpetaUploads + nombre);

            try {
                Files.copy(archivo.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Foto foto = new Foto(idUsuario, nombre, orden);
            guardadas.add(service.guardar(foto));
        }

        return ResponseEntity.ok(guardadas);
    }

    /**
     * Lista todas las fotos de un usuario.
     *
     * @param idUsuario Id del usuario dueño de las fotos
     * @return Lista de fotos del usuario
     */
    @RequestMapping(value = "/api/foto/usuario/{idUsuario}",
            method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<List<Foto>> listarPorUsuario(@PathVariable Long idUsuario) {
        List<Foto> lista = service.getPorUsuario(idUsuario);
        return ResponseEntity.ok(lista);
    }

    /**
     * Elimina una foto por su id.
     *
     * @param id Identificador de la foto
     * @return Respuesta vacia indicando que la operacion termino
     */
    @CrossOrigin
    @RequestMapping(value = "/api/foto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> eliminarFoto(@PathVariable Long id) {
        service.eliminarFoto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/foto/archivo/{nombre:.+}")
    @CrossOrigin
    public ResponseEntity<Resource> obtenerArchivo(@PathVariable String nombre) {
        try {
            Path path = Paths.get("src/main/resources/uploads/" + nombre);
            Resource file = new UrlResource(path.toUri());

            if (file.exists() && file.isReadable()) {
                return ResponseEntity.ok()
                        .header("Content-Disposition", "inline; filename=\"" + file.getFilename() + "\"")
                        .body(file);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
