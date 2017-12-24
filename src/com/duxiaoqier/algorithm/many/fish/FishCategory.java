package com.duxiaoqier.algorithm.many.fish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FishCategory {
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

