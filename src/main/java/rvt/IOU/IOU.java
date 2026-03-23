package rvt.IOU;

public class IOU {
    public void setSum(String toWhom, double amount) {

    }
    public double howMuchDoIOweTo(String toWhom) {
        return 0;
    }

    public static void main(String[] args) {
        IOU mattsIOU = new IOU();
        mattsIOU.setSum("Arthur", 51.5);
        mattsIOU.setSum("Michael", 30);

        System.out.println(mattsIOU.howMuchDoIOweTo("Arthur"));
        System.out.println(mattsIOU.howMuchDoIOweTo("Michael"));
    }
}