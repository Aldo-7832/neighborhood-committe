package mx.edu.utez.neighborhoodcommitte.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class ImagenUtileria {
    public static String guardarImagen(MultipartFile multipartFile, String ruta) {
		String nombreImagen = multipartFile.getOriginalFilename();
		try {
			String rutaArchivo = ruta + "/" + nombreImagen.replaceAll(" ", "").replaceAll("-", "").replace("°", "");
			File imagen = new File(rutaArchivo);
			multipartFile.transferTo(imagen);
			return nombreImagen;
		} catch (IOException e) {
			e.printStackTrace();
			return "null";
		}

	}
}
