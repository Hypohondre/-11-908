public class SegmentTree {
    long[] t;
    long[] tAdd;

    public SegmentTree(long[] a) {
         this.t = new long[4*a.length];
         this.tAdd = new long[4*a.length];
    }


    public void build(int v, int vl, int vr, int[] a){
        if (vl == vr) {
            t[v] = a[vl];
        } else {
            int vm = vl + (vr - vl) / 2;
            build(2 * v + 1, vl, vm, a);
            build(2 * v + 2, vl, vm, a);
            t[v] = t[2 * v + 1] + t[2 * v + 2];
        }
    }

    public long sum(int v, int vl, int vr, int l, int r){
        push(v, vl, vr);
        if (r < vl || vr < l) return 0;
        if (l <= vl && vr <= r) return t[v];
        int vm = vl + (vr - vl)/2;
        long sl = sum(2*v+1, vl, vm, l, r);
        long sr = sum(2*v+2, vm + 1, vr, l, r);
        return sl + sr;
    }

    public void modify(int v, int vl, int vr, int l, int r, int val) {
        push(v, vl, vr);
        if (!(r < vl || vr < l)) {
            if (l <= vl && vr <= r) { push(v, vl, vr); }
            else {
                int vm = vl + (vr - vl) / 2;
                modify(2 * v + 1, vl, vm, l, r, val);
                modify(2 * v + 2, vm + 1, vr, l, r, val);
                t[v] = t[2 * v + 1] + t[2 * v + 2];
            }
        }
    }

    public void push(int v, int vl, int vr) {
        if (tAdd[v] != 0) {
            t[v] += tAdd[v] * (vr - vl + 1);
            if (vl != vr) {
                tAdd[2*v+1] += tAdd[v];
                tAdd[2*v+1] += tAdd[v];
            }
            tAdd[v] = 0;
        }
    }
}
