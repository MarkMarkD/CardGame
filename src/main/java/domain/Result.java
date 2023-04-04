package domain;

public class Result {
    private Card actionCard;
    private final ResultType resultType;

    public Result(ResultType resultType) {
        this.resultType = resultType;
    }

    public Result(ResultType resultType, Card actionCard) {
        this(resultType);
        this.actionCard = actionCard;
    }

    public Card getActionCard() {
        return actionCard;
    }

    public void setActionCard(Card actionCard) {
        this.actionCard = actionCard;
    }

    public ResultType getResultType() {
        return resultType;
    }
}
