
public class AntException extends Exception
{
    public enum antExceptionEnum{ ToDelete, ToRegister }

    public antExceptionEnum state;

    public Ant ant;
}
