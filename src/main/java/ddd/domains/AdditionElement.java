package ddd.domains;

import java.util.Objects;

public class AdditionElement {
    private final int value;

    public AdditionElement (int value){
        if (value < 0) {
            throw new IllegalArgumentException("負の数は扱いません：" + value);
        }
        this.value = value;
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (!(o instanceof  AdditionElement)) return false;
        AdditionElement that = (AdditionElement) o;
        return value == that.value;
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }

    public  int getValue(){
        return this.value;
    }

    public AdditionElement plus(AdditionElement element){
        return new AdditionElement(this.value + element.getValue());
    }
}
