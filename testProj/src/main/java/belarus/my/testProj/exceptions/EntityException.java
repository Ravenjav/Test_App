package belarus.my.testProj.exceptions;

public class EntityException extends Exception{
    private static final long serialVersionUID = 1L;

    public EntityException(){
        super();
    }

    public EntityException(String massage){
        super(massage);
    }

    public EntityException(Exception e){
        super(e);
    }

    public EntityException(String massage, Exception e){
        super(massage, e);
    }
}
