package planificador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class LectorCSV {
    private LectorCSV() {}

    public static List<Proceso> leer(Path fichero) throws IOException {
        List<Proceso> procesos = new ArrayList<>();
        int numLinea = 0;
        for (String linea : Files.readAllLines(fichero)) {
            numLinea++;
            linea = linea.strip();
            if (linea.isEmpty() || linea.startsWith("#")) continue;
            String[] partes = linea.split(";");
            if (partes.length != 3) {
                throw new IllegalArgumentException("Linea " + numLinea + " mal formada: " + linea);
            }
            try{
                procesos.add(new Proceso(partes[0].strip(),
                        Integer.parseInt(partes[1].strip()),
                        Integer.parseInt(partes[2].strip()),
                        procesos.size()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Linea " + numLinea + ": llegada y ráfaga deben ser enteros");
            }
        }
        if (procesos.isEmpty()) throw new IllegalArgumentException("El fichero no contiene procesos");
        return procesos;
    }
}
