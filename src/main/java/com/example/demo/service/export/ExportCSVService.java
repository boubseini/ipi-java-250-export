package com.example.demo.service.export;

        import com.example.demo.dto.ClientDTO;
        import org.springframework.stereotype.Service;
        import java.io.IOException;
        import java.io.Writer;
        import java.util.List;
@Service
public class ExportCSVService {

    public void export(Writer printWriter, List<ClientDTO> clients) throws IOException {
        //printWriter.write(" hello bouba ");

        printWriter.write("Nom;Prenom;Adresse;Age");
        printWriter.write("\n");
        for (ClientDTO client : clients) {
            printWriter.write(client.getNom().replace(";", ""));
            printWriter.write(";");
            printWriter.write(client.getPrenom().replace(";", ""));
            printWriter.write(";");
            printWriter.write(client.getAdresse());
            printWriter.write(";");
            printWriter.write(client.getAge().toString());
            printWriter.write(";");
            printWriter.write("\n");


        }

    }
}