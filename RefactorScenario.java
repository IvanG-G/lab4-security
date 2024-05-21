import java.sql.PreparedStatement;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.List;

public class RefactorScenario {
    
    public boolean authenticateUser(String userName, String passWord){
        //Connection to DB
        var con = dataSource.getConnection();

        var query = "SELECT * from UserTable where username = ? AND password = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, userName);
        statement.setString(2, passWord);
        var found = Optional.of(statement.executeQuery());
        return found.isPresent();
    }


    public JWT generateJWT(List<String> userCredentials) throws Exception{
        if (authenticateUser(userCredentials.get(0), userCredentials.get(1))){
            var tokenExpirationTime = 3600;
            return JWT.builder()
                .setClaims(null).setSubject(user.getUsername().setSubject(userCredentials.get(0)))
                .setIssuedAt(OffsetDateTime.now())
                .setExpiration(OffsetDateTime.now().plus())
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
        }
        else{
            throw new Exception();
        }
    }
}
