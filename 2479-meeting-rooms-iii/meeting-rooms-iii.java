class Solution {
    class Pair {
        Long first , second;

        Pair(Long first , Long second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "{ " + this.first + " , " + this.second + " }";
        }
    }

    class RoomComp implements Comparator<Pair> {
        @Override
        public int compare(Pair L , Pair R) {
            if(L.first.compareTo(R.first) == 0) 
                return L.second.compareTo(R.second);

            return L.first.compareTo(R.first);
        }
    }

    public int mostBooked(int n, int[][] M) {
        TreeSet<Pair> ts = new TreeSet<>(new RoomComp());
        
        ArrayList<Pair> R = new ArrayList<>();

        for(int i = 0; i < M.length; ++i) 
            R.add(new Pair((long)M[i][0] , (long)M[i][1]));

        Collections.sort(R , new RoomComp());
         
        for(int i = 0; i < n; ++i) 
            ts.add(new Pair((long)0 , (long)i));

        TreeSet<Long> qu = new TreeSet<>();  

        long [] ans = new long [n];  

        for(int i = 0; i < R.size(); ++i) {
            long s = R.get(i).first , e = R.get(i).second;

            while(ts.size() > 0) {
                Pair C = ts.first();

                if(C.first <= s) {
                    qu.add(C.second);
                    ts.remove(C);
                } else 
                   break;
            }

            if(qu.size() > 0) {
                Long C = qu.first();
                qu.remove(C);
                long rNo = C;

                ans[(int)rNo]++;
                
                ts.add(new Pair(R.get(i).second , C ));
            } else {
                Pair C = ts.first();
                long S = C.first , rNo = C.second;

                ts.remove(C);

                ans[(int)rNo]++;

                ts.add(new Pair( S + (e - s) , rNo ));
            }
        }

        long mx = 0;
        int Ans = -1;

        for(int i = 0; i < n; ++i) 
            if(ans[i] > mx) {
                mx = ans[i];
                Ans = i;
            }         

        return Ans;    
    }
}