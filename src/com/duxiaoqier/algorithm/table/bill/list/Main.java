package com.duxiaoqier.algorithm.table.bill.list;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Bill> billList = new ArrayList<>();
        billList.add(new Bill(7, 0, 1));
        billList.add(new Bill(8, 0, 1));
        billList.add(new Bill(9, 7, 1));
        billList.add(new Bill(10, 0, 1));
        billList.add(new Bill(11, 10, 1));
        billList.add(new Bill(12, 11, 1));
        billList.add(new Bill(13, 12, 1));
        billList.add(new Bill(14, 13, 1));
        billList.add(new Bill(15, 14, 1));
        billList.add(new Bill(16, 15, 1));
        billList.add(new Bill(17, 8, 1));

        List<Bill> result = new ArrayList<>();
        int currentId = 16;
        Bill currentBill = billList.stream().filter(p->p.getId() == currentId).findFirst().get();
        while (currentBill.getPreId() != 0){
            result.add(currentBill);
            final Bill finalCurrentBill = currentBill;
            currentBill = billList.stream().filter(p->p.getId() == finalCurrentBill.getPreId()).findFirst().get();
        }
        result.add(currentBill);
        result.forEach(System.out::println);
    }
}

class Bill{
    private int id;
    private int preId;
    private int rentStatus;

    public Bill(int id, int pre_id, int rentStatus) {
        this.id = id;
        this.preId = pre_id;
        this.rentStatus = rentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreId() {
        return preId;
    }

    public void setPreId(int preId) {
        this.preId = preId;
    }

    public int isRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(int rentStatus) {
        this.rentStatus = rentStatus;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", preId=" + preId +
                ", rentStatus=" + rentStatus +
                '}';
    }
}