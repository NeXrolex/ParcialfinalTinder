package co.uDistrital.avanzada.parcialFinal.TinderUD.foto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
 * Controla todas las acciones y endpoint de las fotos
 * 
 * @author Alex, Steven
 */
@RestController
public class FotoController {

    @Autowired
    private FotoService service;

    // Carpeta temporal para almacenar fotos mientras corre el servidor
    private final Path carpetaUploads;

    public FotoController() throws IOException {
        carpetaUploads = Files.createTempDirectory("tinderud-uploads");
        carpetaUploads.toFile().deleteOnExit(); // Borra al cerrar JVM
    }

    @RequestMapping(value = "/api/foto", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Foto> crearFoto(@RequestBody Foto foto) {
        Foto guardada = service.guardar(foto);
        return ResponseEntity.ok(guardada);
    }

    @PostMapping(value = "/api/foto/subir/{idUsuario}",
            consumes = "multipart/form-data")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Foto>> subirFotos(
            @PathVariable Long idUsuario,
            @RequestParam("fotos") List<MultipartFile> archivos,
            @RequestParam("orden") List<Integer> ordenes
    ) throws IOException {

        List<Foto> guardadas = new ArrayList<>();

        for (int i = 0; i < archivos.size(); i++) {
            MultipartFile archivo = archivos.get(i);
            int orden = ordenes.get(i);

            String nombre = System.currentTimeMillis() + "_"
                    + archivo.getOriginalFilename();
            Path ruta = carpetaUploads.resolve(nombre);

            Files.copy(archivo.getInputStream(), ruta, java.nio.file
                    .StandardCopyOption.REPLACE_EXISTING);

            Foto foto = new Foto(idUsuario, nombre, orden);
            guardadas.add(service.guardar(foto));
        }

        return ResponseEntity.ok(guardadas);
    }

    @RequestMapping(value = "/api/foto/usuario/{idUsuario}",
            method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<List<Foto>> listarPorUsuario(@PathVariable
            Long idUsuario) {
        List<Foto> lista = service.getPorUsuario(idUsuario);
        return ResponseEntity.ok(lista);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/foto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> eliminarFoto(@PathVariable Long id)
            throws IOException {
        Foto f = service.getPorId(id);
        if (f != null) {
            Path ruta = carpetaUploads.resolve(f.getUrl());
            Files.deleteIfExists(ruta);
        }
        service.eliminarFoto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/foto/archivo/{nombre:.+}")
    @CrossOrigin
    public ResponseEntity<Resource> obtenerArchivo(@PathVariable 
            String nombre) {
        try {
            Path path = carpetaUploads.resolve(nombre);
            Resource file = new UrlResource(path.toUri());

            if (file.exists() && file.isReadable()) {
                return ResponseEntity.ok()
                        .header("Content-Disposition", "inline; filename=\""
                                + file.getFilename() + "\"")
                        .body(file);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
