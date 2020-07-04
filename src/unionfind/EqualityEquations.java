package unionfind;

public class EqualityEquations {
    public static void main(String[] args) {
        EqualityEquations obj = new EqualityEquations();
        System.out.println(obj.equationsPossible(new String[]{"a==b", "b!=a"})); // false
        System.out.println(obj.equationsPossible(new String[]{"a==b", "b==a"})); // true
        System.out.println(obj.equationsPossible(new String[]{"a==b", "b==c", "a==c"}));//true
        System.out.println(obj.equationsPossible(new String[]{"a==b", "b!=c", "c==a"}));//false
        System.out.println(obj.equationsPossible(new String[]{"c==c", "b==d", "x!=z"}));//true
    }

    public boolean equationsPossible(String[] equations) {
        DisjointSet set = new DisjointSet(26);

        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                set.union(equation.charAt(0) - 'a', equation.charAt(3) - 'a');
            }
        }

        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                if (set.find(equation.charAt(0) - 'a') == set.find(equation.charAt(3) - 'a')) {
                    return false;
                }
            }
        }

        return true;
    }

    static class DisjointSet {
        int n;
        int[] parents;
        int[] rank;

        public DisjointSet(int n) {
            this.n = n;
            this.parents = new int[n];
            this.rank = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (parents[x] == x) {
                return x;
            }
            return parents[x] = find(parents[x]);
        }

        public void union(int i, int j) {
            int pI = find(i);
            int pJ = find(j);

            if (pI == pJ) return;

            int rI = rank[i];
            int rJ = rank[j];

            if (rI > rJ) {
                parents[pJ] = pI;
            } else if (rJ > rI) {
                parents[pI] = pJ;
            } else {
                parents[pJ] = pI;
                rank[pI] += 1;
            }
        }
    }

}
