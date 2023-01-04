import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.sql.Date;

public class Principal {

    public static void main(String[] args) {

        MongoClient client = new MongoClient();
        var database = client.getDatabase("local");
        var users = database.getCollection("startup_log");
        var user = users.find().first();
        System.out.println(user);


       var newUser =  new Document("nome", "Pedro")
                .append("data_nascimento", new Date(2002,12,12))
                .append("profissao", "programador")
                .append("funcao", "integração");

       //cria
       users.insertOne(newUser);

       //atualiza
       users.updateOne(Filters.eq("nome", "Pedro"),
               new Document("$set", new Document("nome", "Pedro Nascimento")));

       //deleta
        users.deleteOne(Filters.eq("nome", "Pedro Nascimento"));

    }
}
