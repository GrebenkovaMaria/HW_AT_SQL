package ru.netology.data;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.DriverManager;


public class DataHelper {

      public static User getAuthInfo() {
      return new User("vasya", "qwerty123");
      }

      public static User getOtherAuthInfo() {
        return new User("petya", "123qwerty");
    }

      public static User getInvalidAuthInfo() {
          Faker faker = new Faker();
          String invalidLogin = faker.letterify("??????");
          String invalidPassword = faker.letterify("???12????????");
          return new User (invalidLogin, invalidPassword);
    }

      public static String getInvalidAuthCode(){
          Faker faker = new Faker();
          String invalidAuthCode =faker.numerify("######");
          return invalidAuthCode;
      }

    @SneakyThrows
    public static void clearAuthCode(){
        QueryRunner runner = new QueryRunner();
        String deleteOldAuthCode = "DELETE FROM auth_codes WHERE created < NOW() - INTERVAL 2 SECOND ;" ;
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app_db", "user", "12345678"
        );
        runner.update(conn, deleteOldAuthCode);
    }


    @SneakyThrows
    public static AuthCodes getAuthCode(User user)  {
          clearAuthCode();
        QueryRunner runner = new QueryRunner();
        String login = user.getLogin();
        String userIdSQL = "SELECT id FROM users WHERE login =" + " " + "'" + login + "'" + ";";
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app_db", "user", "12345678"
                );
        String userId = runner.query(conn, userIdSQL,new ScalarHandler<>());
        String authCodesSQL = "SELECT code FROM auth_codes WHERE user_id =" + " " + "'" + userId + "'" + ";" ;
        AuthCodes authCode = runner.query(conn, authCodesSQL, new BeanHandler<>(AuthCodes.class));
        return authCode;
    }

    @SneakyThrows
    public static void cleanDb(){
        QueryRunner runner = new QueryRunner();
        String deleteAllAuthCode = "DELETE FROM auth_codes;" ;
        String deleteAllCards = "DELETE FROM cards;" ;
        String deleteAllUsers = "DELETE FROM users;" ;
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app_db", "user", "12345678"
        );
        runner.update(conn, deleteAllAuthCode);
        runner.update(conn, deleteAllCards);
        runner.update(conn, deleteAllUsers);
    }
 }




