package org.example.tuktuksparedepot;

import org.example.tuktuksparedepot.objects.sparePart;

public class TestRun {
    public static void main(String[] args) {
        sparePart part1=new sparePart("P001","wheel","bajaj",7000,6,"chasis","2026-01-02","wheel.png");
        System.out.println(part1.getPartCode());

        part1.setPartCode("0002");
        System.out.println(part1.getPartCode());


    }
}
