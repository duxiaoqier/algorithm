package com.duxiaoqier.algorithm.segment.max;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 过去最大线段长度
 */
public class Main {

    public static void main(String[] args) {

        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(3, 6));
        segmentList.add(new Segment(7, 9));
        segmentList.add(new Segment(1, 4));
        segmentList.add(new Segment(10, 15));
        segmentList.add(new Segment(8, 13));

        System.out.println(getMaxSegment(segmentList));
    }

    private static int getMaxSegment(List<Segment> segmentList) {
        //关键步骤，按照开始位置进行排序
        segmentList.sort(Comparator.comparing(Segment::getStart));

        Segment firstSegment = segmentList.get(0);
        int max = firstSegment.getEnd() - firstSegment.getStart();//除最后一段最大长度
        int previousLength = max;//合并线段长度

        for (int i = 1; i < segmentList.size(); i++) {
            Segment currentSegment = segmentList.get(i);
            Segment previousSegment = segmentList.get(i - 1);

            // 如果当前开始的位置，在前一个线段结束位置之前说明可以合并
            // 如果当前开始位置，在前一个线段结束位置之后，说明空开了，这是前面线段合并结束的标志
            if (previousSegment.getEnd() > currentSegment.getStart()) {
                if (previousSegment.getEnd() < currentSegment.getEnd()) {
                    previousLength = currentSegment.getEnd() - previousSegment.getStart();
                }
            } else {
                if (previousLength > max) {
                    max = previousLength;
                }
                previousLength = currentSegment.getEnd() - currentSegment.getStart();
            }
        }

        return max > previousLength ? max : previousLength;
    }
}
