package chess;

import java.util.Set;

public class StubAttackService implements Main.AttackService {
    private Set<Position> attackResult = Set.of();
    private int countResult = 0;

    public void setAttackResult(Set<Position> result) {
        this.attackResult = result;
    }

    public void setCountResult(int result) {
        this.countResult = result;
    }

    @Override
    public Set<Position> calculateAttack(Board board) {
        return attackResult;
    }

    @Override
    public int count(Board board) {
        return countResult;
    }
}
