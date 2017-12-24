package com.duxiaoqier.algorithm.many.fish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
/**
 * 链接：https://www.nowcoder.com/questionTerminal/e3dd485dd23a42899228305658457927?orderByHotValue=0&page=1&onlyReference=false
 来源：牛客网

 牛牛有一个鱼缸。鱼缸里面已经有n条鱼，每条鱼的大小为fishSize[i] (1 ≤ i ≤ n,均为正整数)，牛牛现在想把新捕捉的鱼放入鱼缸。鱼缸内存在着大鱼吃小鱼的定律。经过观察，牛牛发现一条鱼A的大小为另外一条鱼B大小的2倍到10倍(包括2倍大小和10倍大小)，鱼A会吃掉鱼B。考虑到这个，牛牛要放入的鱼就需要保证：
 1、放进去的鱼是安全的，不会被其他鱼吃掉
 2、这条鱼放进去也不能吃掉其他鱼
 鱼缸里面已经存在的鱼已经相处了很久，不考虑他们互相捕食。现在知道新放入鱼的大小范围[minSize,maxSize](考虑鱼的大小都是整数表示),牛牛想知道有多少种大小的鱼可以放入这个鱼缸。
 */

/**
 * 此方法表示鱼动态加入的，和题意不是很相符
 * 此方法有bug，如果修复bug并忽略排序损耗，按照这一思路，理论上可以达到时间复杂度为O(n)
 */
public class FishCategoryDynamicInput {
    public static void main(String[] args) {

//接受输入参数
        Scanner scanner = new Scanner(System.in);
        int minSize = scanner.nextInt(); //新放入鱼范围的最小值
        int maxSize = scanner.nextInt();//新放入鱼范围的最大值
        int inNumber = scanner.nextInt();//鱼缸里面已经有鱼的数量
        List<Integer> fishSizeList = new ArrayList<>();
        for (int i = 0; i < inNumber; i++) {
            fishSizeList.add(scanner.nextInt());//初始化已经有的鱼的大小inFishSize[i]
        }


//        fishSizeList.add(1000);
//        fishSizeList.add(700);
//        fishSizeList.add(354);
//        fishSizeList.add(20);


        Segment startSegment = getStartSegment(fishSizeList);
        if (maxSize > startSegment.end * 10) {
            fishSizeList.add(maxSize);
        } else if (maxSize > startSegment.start * 2 - 1 && maxSize > startSegment.end) {
            fishSizeList.add(startSegment.start * 2 - 1);
        } else if (maxSize <= startSegment.start * 2 - 1 && maxSize > startSegment.end) {
            fishSizeList.add(maxSize);
        }

        Segment endSegment = getEndSegment(fishSizeList);
        if (minSize <= (endSegment.end - 1) / 10) {
            fishSizeList.add(minSize);
        } else if (minSize * 2 - 1 >= endSegment.end && minSize < endSegment.start) {
            fishSizeList.add(minSize);
        } else if (minSize * 2 - 1 < endSegment.end && minSize < endSegment.start) {
            fishSizeList.add(endSegment.end);
        }

        fishSizeList = fishSizeList.stream().filter(p -> p <= maxSize && p >= minSize).collect(Collectors.toList());

        List<Segment> segmentList = calculateWithInSortedList(fishSizeList);
        int sum = segmentList.stream().mapToInt(Segment::getSize).sum();
        System.out.println(segmentList);
        System.out.println(sum);
    }

    private static Segment getEndSegment(List<Integer> fishSizeList) {

        Collections.sort(fishSizeList);
        int size = fishSizeList.size();
        int max = fishSizeList.get(0);
        int min = max;
        for (int i = 0; i < size - 1; i++) {
            int nextFishSize = fishSizeList.get(i + 1);
            if (max * 2 > nextFishSize) {
                max = nextFishSize;
            } else {
                return new Segment(min, max);
            }
        }
        return new Segment(fishSizeList.get(0), fishSizeList.get(0));
    }

    private static Segment getStartSegment(List<Integer> fishSizeList) {
        Collections.sort(fishSizeList);
        int size = fishSizeList.size();
        int max = fishSizeList.get(size - 1);
        int min = max;
        for (int i = size - 1; i > 0; i--) {
            int previousFishSize = fishSizeList.get(i - 1);
            if (min / 2 < previousFishSize) {
                min = previousFishSize;
                if (min * 2 - 1 < max) {
                    max = min * 2 - 1;
                }
            } else {
                return new Segment(min, max);
            }
        }
        return new Segment(fishSizeList.get(0), fishSizeList.get(0));
    }

    private static List<Segment> calculateWithInSortedList(List<Integer> fishSizeList) {
        if (fishSizeList.size() <= 1) return Collections.emptyList();
        Collections.sort(fishSizeList);

        List<Segment> segmentList = new ArrayList<>();
        int size = fishSizeList.size();
        int max = fishSizeList.get(size - 1);
        int min = max;
        for (int i = size - 1; i > 0; i--) {
            int previousFishSize = fishSizeList.get(i - 1);
            if (min / 2 < previousFishSize) {
                min = previousFishSize;
                if (min * 2 - 1 < max) {
                    max = min * 2 - 1;
                }
            } else {
                int minSeg = max / 20;
                if (previousFishSize * 200 + 1 < max) {
                    min = max / 2 + 1;
                    segmentList.add(new Segment(min, max));

                    max = minSeg;
                    if (max / 2 < previousFishSize * 10 + 1) {
                        min = previousFishSize * 10 + 1;
                    } else {
                        min = max / 2 + 1;
                    }

                    segmentList.add(new Segment(min, max));
                    max = previousFishSize;
                    min = max;
                    continue;
                }

                if (previousFishSize > minSeg) {
                    min = previousFishSize * 10 + 1;
                    segmentList.add(new Segment(min, max));
                    max = previousFishSize;
                    min = max;
                } else {
                    segmentList.add(new Segment(max / 2 + 1, max));

                    if (previousFishSize >= minSeg / 2 + 1) {
                        max = minSeg;
                        min = previousFishSize;
                    } else {
                        max = previousFishSize * 2 - 1;
                        min = previousFishSize;
                    }
                }
            }
        }
        segmentList.add(new Segment(min, max));
        return segmentList;
    }

    static class Segment {
        private int start;
        private int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getSize() {
            return end - start + 1;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}

