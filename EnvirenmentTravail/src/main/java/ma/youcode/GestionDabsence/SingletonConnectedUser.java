package ma.youcode.GestionDabsence;


import ma.youcode.GestionDabsence.Modeles.User;

import java.sql.SQLException;

public class SingletonConnectedUser {

    public User connectedUser;
    private SingletonConnectedUser(User target) throws SQLException, ClassNotFoundException
    {
        this.connectedUser = target;
    }

    public static SingletonConnectedUser getSingletonConnectedUser(User addTarget) throws SQLException, ClassNotFoundException
    {
        if (ref == null)
            // it's ok, we can call this constructor
            ref = new SingletonConnectedUser(addTarget);
        return ref;
    }

    public Object clone()
            throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
        // that'll teach 'em
    }

    private static SingletonConnectedUser ref;
}
