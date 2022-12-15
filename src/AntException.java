
//Exception permettant de transmettre au système gérant les fourmis si une fourmi a été détruite ou si elle a trouvé un chemin
public class AntException extends Exception
{
    public enum antExceptionEnum{ ToDelete, ToRegister }

    public antExceptionEnum state; //Etat de la fourmi : detruite ou a  trouvé un chemin

    public Ant ant; //La fourmi en question
}
