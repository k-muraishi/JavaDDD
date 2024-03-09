package ddd.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final String name; // 不変でユニークな識別子
    private final List<AdditionFormula> history = new ArrayList<>(); // 可変の属性（List参照は不変だが中身は可変）

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(this.name, user.name); // 名前で識別する
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return this.name;
    }

    public List<AdditionFormula> getHistory() {
        return new ArrayList<>(this.history);
    }

    public void addHistory(AdditionElement e1, AdditionElement e2, AdditionElement result) {
        this.history.add(new AdditionFormula(e1, e2, result));
    }
}
