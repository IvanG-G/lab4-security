# lab4-security

## Scneario 1
```
FUNCTION authenticateUser(username, password):
  QUERY database WITH username AND password
  IF found RETURN True
  ELSE RETURN False
```
Posible SQL Injection in the code above, using the UserName and Password directly into the query, the possible solution could be validate the credentials before introduce them into the query, or using parameterized queries.

Parameterized Queries
```
FUNCTION authenticateUser(username, password):
    var query = "QUERY database WITH username = ? AND password = ?" 
    vat statement = statement(query)
    statement.setString(1, username)
    statement.setString(2, password)
    var found = statement.executeQuery
    IF found RETURN True
    ELSE RETURN False
```

Java class
```Java
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
```
