package com.zhuweihao.algorithm.GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲，给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多，返回最多的宣讲场次
 *
 * @Author zhuweihao
 * @Date 2023/6/2 17:29
 * @Description com.zhuweihao.algorithm.GreedyAlgorithm
 */
public class BestArrange {

    static class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int getBestArrangeMeetingNumber1(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }
        return process(meetings, 0, 0);
    }

    public static int process(Meeting[] meetings, int done, int time) {
        if (meetings.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < meetings.length; i++) {
            if (meetings[i].start > time) {
                Meeting[] lastMeetings = remove(meetings, i);
                int process = process(lastMeetings, done + 1, meetings[i].end);
                max = Math.max(max, process);
            }
        }
        return max;
    }

    public static Meeting[] remove(Meeting[] meetings, int index) {
        Meeting[] ans = new Meeting[meetings.length - 1];
        for (int i = 0; i < meetings.length - 1; i++) {
            if (i < index) {
                ans[i] = meetings[i];
            } else {
                ans[i] = meetings[i + 1];
            }
        }
        return ans;
    }

    public static class MyComparator implements Comparator<Meeting> {

        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }

    public static int getBestArrangeMeetingNumber2(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }
        Arrays.sort(meetings, new MyComparator());
        int ans = 0;
        int time = 0;
        for (int i = 0; i < meetings.length; i++) {
            if (meetings[i].start > time) {
                ans++;
                time = meetings[i].end;
            }
        }
        return ans;
    }

    // for test
    public static Meeting[] generateMeetings(int MeetingSize, int timeMax) {
        Meeting[] ans = new Meeting[(int) (Math.random() * (MeetingSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Meeting(r1, r1 + 1);
            } else {
                ans[i] = new Meeting(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int MeetingSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Meeting[] Meetings = generateMeetings(MeetingSize, timeMax);
            if (getBestArrangeMeetingNumber1(Meetings) != getBestArrangeMeetingNumber2(Meetings)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
