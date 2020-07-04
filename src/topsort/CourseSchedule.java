package topsort;

import java.util.*;

//207 https://leetcode.com/problems/course-schedule/
public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule obj = new CourseSchedule();

        System.out.println();
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        if (prerequisites.length == 0 || prerequisites[0].length == 0) return true;

        Map<Integer, List<Integer>> graph = buildGraph(prerequisites, indegrees);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        int n = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            n++;
            int curr = queue.poll();

            for (int neigh : graph.get(curr)) {
                if (--indegrees[neigh] == 0) {
                    queue.add(neigh);
                }
            }
        }

        return n == numCourses;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] prerequisites, int[] indegrees) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] p : prerequisites) {
            graph.putIfAbsent(p[0], new ArrayList<>());
            graph.get(p[0]).add(p[1]);
            indegrees[p[1]]++;
        }
        return graph;
    }
}
