import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.util.jooqtest.Tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String userName = "postgres";
        String password = "";
        String url = "jdbc:postgresql://localhost:5432/testjooq";
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("key", new JsonPrimitive("value"));
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES_9_5);
            create.insertInto(Tables.JSONTODO, Tables.JSONTODO.DATAJSON).values(jsonObject).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
