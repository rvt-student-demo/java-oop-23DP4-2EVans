package rvt.IOU;

import java.util.HashMap;
import java.util.Map;

public class IOU {

    private final Map<String, Double> debts;

    public IOU() {
        this.debts = new HashMap<>();
    }

    public void setSum(String toWhom, double amount) {
        if (toWhom == null) {
            return;
        }
        debts.put(toWhom, amount);
    }

    public double howMuchDoIOweTo(String toWhom) {
        if (toWhom == null) {
            return 0;
        }
        return debts.getOrDefault(toWhom, 0.0);
    }
}

