

public class CannotBuildPyramidException extends RuntimeException {
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println("Из этого массива нельзя построить достойную пирамиду");
    }
}
