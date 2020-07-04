package topsort;

import java.util.*;

// https://leetcode.com/problems/course-schedule-ii/
public class CourseSchedule2 {
    public static void main(String[] args) {
        CourseSchedule2 obj = new CourseSchedule2();
        System.out.println(Arrays.toString(obj.findOrder(2, new int[][]{})));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        if (prerequisites.length == 0 || prerequisites[0].length == 0) return new int[0];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegrees = new int[numCourses];

        for (int[] pre : prerequisites) {
            graph.putIfAbsent(pre[1], new ArrayList<>());
            graph.get(pre[1]).add(pre[0]);
            indegrees[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int n = 0;
        int[] res = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res[idx++] = curr;
            n++;
            if (graph.containsKey(curr)) {
                for (int i : graph.get(curr)) {
                    if (--indegrees[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }

        return n == numCourses ? res : new int[0];
    }

}
