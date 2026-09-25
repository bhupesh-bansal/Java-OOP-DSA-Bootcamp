package E.AbstractClasses_Interfaces.Q4;

public class PositiveValidator implements MathUtils.Validator{

    @Override
    public boolean isValid(int n) {
        return n>0;
    }
}
