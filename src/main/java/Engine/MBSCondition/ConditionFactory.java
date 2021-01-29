package Engine.MBSCondition;

public class ConditionFactory {

    public static Condition getCondition(String key) {
        Condition condition = new Condition();
        Condition conditionRange = new ConditionRange();
        Condition conditionList = new ConditionList();

        if (key.contains("Range")) {
            return conditionRange;
        } else if (key.contains("List")) {
            return conditionList;
        } else {
            return condition;
        }

    }


}
