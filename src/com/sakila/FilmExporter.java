import com.sakila.data.Film;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmExporter {  

    public void exportFilmsToJson(List<Film> films) {  
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("films.json"), films);  
        } catch (IOException e) {
            e.printStackTrace();  
        }
    }
}